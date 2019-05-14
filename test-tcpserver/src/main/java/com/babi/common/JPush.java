package com.babi.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Encoder;

/**
 * 
 * @author Admin
 *
 */
public class JPush {

	private static String masterSecret = "8abb4b0ef32f4233e278cd99";
	private static String appKey = "797aa98164dddd5ef5eb00d5";
	private static String pushUrl = "https://api.jpush.cn/v3/push";
	private static boolean apns_production = false;// false表示发送给开发环境，true表示发送给实际环境
	private static int time_to_live = 86400;

	public static String BROADCAST = "2";
	public static String PERSONAL = "1";

	/**
	 * 
	 * @param alert
	 *            极光推送内容
	 * @param alias
	 *            推送别名
	 * @param type
	 *            1针对个人别名的推送 2.针对所有app用户消息的推送
	 * 
	 */
	public static void jiguangPush(String alert, String alias, String type) {
		try {
			String result = push(pushUrl, alias, alert, appKey, masterSecret, apns_production, time_to_live, type);
			JSONObject resData = JSON.parseObject(result);
			if (resData.containsKey("error")) {
				CommonLog.info(JPush.class, "针对别名为" + alias + "的信息推送失败！");
				JSONObject error = JSON.parseObject(String.valueOf(resData.get("error")));
				CommonLog.info(JPush.class, "错误信息为：" + error.get("message").toString());
			}
			CommonLog.info(JPush.class, "针对别名为" + alias + "的信息推送成功！");
		} catch (Exception e) {
			CommonLog.info(JPush.class, "针对别名为" + alias + "的信息推送失败！");
			CommonLog.error(JPush.class, e);
		}
	}

	/**
	 * 组装极光推送专用json串
	 * 
	 * @param alias
	 * @param alert
	 * @return json
	 */
	public static JSONObject generateJson(String alias, String alert, boolean apns_production, int time_to_live,
			String information) {
		JSONObject json = new JSONObject();
		JSONArray platform = new JSONArray();// 平台
		platform.add("android");
		platform.add("ios");

		JSONObject audience = new JSONObject();// 推送目标
		JSONArray alias1 = new JSONArray();
		alias1.add(alias);
		audience.put("alias", alias1);

		JSONObject notification = new JSONObject();// 通知内容
		JSONObject android = new JSONObject();// android通知内容
		android.put("alert", alert);
		android.put("builder_id", 1);
		JSONObject android_extras = new JSONObject();// android额外参数
		android_extras.put("type", information);
		android.put("extras", android_extras);

		JSONObject ios = new JSONObject();// ios通知内容
		ios.put("alert", alert);
		ios.put("sound", "default");
		ios.put("badge", "1");// +1
		JSONObject ios_extras = new JSONObject();// ios额外参数
		ios_extras.put("type", information);// 0代表激活消息，1代表普通消息 您有待激活的车位锁

		ios.put("extras", ios_extras);
		notification.put("android", android);
		notification.put("ios", ios);

		JSONObject options = new JSONObject();// 设置参数
		options.put("time_to_live", Integer.valueOf(time_to_live));
		options.put("apns_production", apns_production);

		json.put("platform", platform);

		/**
		 * 如果是新闻公告；APP产品更新信息则为广播推送
		 */
		if ("2".equals(information) || "3".equals(information)) {
			json.put("audience", "all");// 广播推送
		} else {
			json.put("audience", audience);// 别名推送
		}

		json.put("notification", notification);
		json.put("options", options);
		System.out.println("json:" + json);
		return json;

	}

	/**
	 * 推送方法-调用极光API
	 * 
	 * @param reqUrl
	 * @param alias
	 * @param alert
	 * @return result
	 */
	public static String push(String reqUrl, String alias, String alert, String appKey, String masterSecret,
			boolean apns_production, int time_to_live, String information) {
		String base64_auth_string = encryptBASE64(appKey + ":" + masterSecret);
		String authorization = "Basic " + base64_auth_string;
		return sendPostRequest(reqUrl,
				generateJson(alias, alert, apns_production, time_to_live, information).toString(), "UTF-8",
				authorization);
	}

	/**
	 * 发送Post请求（json格式）
	 * 
	 * @param reqURL
	 * @param data
	 * @param encodeCharset
	 * @param authorization
	 * @return result
	 */
	@SuppressWarnings({ "resource" })
	public static String sendPostRequest(String reqURL, String data, String encodeCharset, String authorization) {
		HttpPost httpPost = new HttpPost(reqURL);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		String result = "";
		try {
			StringEntity entity = new StringEntity(data, encodeCharset);
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			httpPost.setHeader("Authorization", authorization.trim());
			response = client.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), encodeCharset);
			System.out.println(result);
		} catch (Exception e) {
			CommonLog.info(JPush.class, "请求通信[" + reqURL + "]时偶遇异常。");
			CommonLog.info(JPush.class, e);
		} finally {
			client.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	 * * BASE64加密工具
	 */
	public static String encryptBASE64(String str) {
		byte[] key = str.getBytes();
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String strs = base64Encoder.encodeBuffer(key);
		return strs;
	}

	public static void main(String[] args) {
		JPush.jiguangPush("您好！", "U1804224900000003", JPush.PERSONAL);
	}

}
