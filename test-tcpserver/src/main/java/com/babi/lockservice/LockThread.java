package com.babi.lockservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.babi.common.CommonLog;
import com.babi.common.Params;
import com.babi.dao.JedisUtils;
import com.babi.dao.LockDAO;
import com.babi.domain.Blood;
import com.babi.domain.Exercise;
import com.babi.domain.Weight;

import redis.clients.jedis.Jedis;


public class LockThread extends Thread {
	Socket socket = null;
	BufferedReader is = null; // 输入流
	PrintWriter os = null; // 输出流

	LockDAO lockdao = new LockDAO();

	/*
	 * 构造方法
	 */
	public LockThread(Socket socket) {
		this.socket = socket;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		String line = null; // 通信节点传来的数据
		String CABINKEY = null; // “LOCK:LOCKID”
		String CabinID = null; // 车位锁ID
		// String IMEI = null;
		// String IMEIKEY = null; // “IMEI:IMEI”
		String door = null;	
		String aqi = null;
		String temperature = null;
		String light = null;
		String color = null;
		String emgstop = null;
		String emgcall = null;
		String duration = null;
		String distance = null;
		String calorie = null;
		String maxspeed =null;
		String res = null;
		String weight = null;
		String hiblood = null;
		String loblood = null;
		String pulse = null;
		Jedis jedis = null;
		String old_door = null;
		String old_aqi = null;
		String old_temperature = null;
		String old_light = null;
		String old_color = null;
		String old_emgstop = null;
		String old_emgcall = null;
	
		Map<String, Object> jsMap = null;

		CommonLog.info(this.getClass(), "通信节点已上线：" + socket);

		try {
			// 设置超时时间
			 socket.setSoTimeout(Params.TIMEOUT); // 阻塞等待情况下无需设置

			// 取得Socket的输入流和输出流
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream(), true);
			
			while ((line = is.readLine()) != null) {
				CommonLog.info(this.getClass(), "来自通信节点的信息：" + line);

				// 收到正常状态信息
 				if (line.indexOf("CabinID") != -1 && line.indexOf("{") == 0 && line.indexOf("}") == line.length()-1) {
					// 接收到设备状态数据的处理----
					// 解析JSON数据
					jsMap = JSON.toJavaObject(JSON.parseObject(line), Map.class);
					CabinID = jsMap.get("CabinID").toString().trim();
					door = jsMap.get("Door").toString().trim();
					aqi = jsMap.get("PM2.5").toString().trim();
					color = jsMap.get("Color").toString().trim();
					temperature = jsMap.get("Temperature").toString().trim();
					light = jsMap.get("Light").toString().trim();
					emgstop = jsMap.get("EMGStop").toString().trim();
					emgcall = jsMap.get("EMGCall").toString().trim();
					//exercise的数据
					if (line.indexOf("Duration") != -1 &&line.indexOf("Distance") != -1
							 &&line.indexOf("Calorie") != -1){
						
						duration = jsMap.get("Duration").toString().trim();
						distance = jsMap.get("Distance").toString().trim();
						calorie = jsMap.get("Calorie").toString().trim();
						maxspeed = jsMap.get("MaxSpd").toString().trim();
						
						if (lockdao.queryExercise(CabinID)) {
							Exercise exercise = lockdao.findExerciseByCabinId(CabinID);
							if (Integer.parseInt(duration)!=0) {
								exercise.setDuration(Float.parseFloat(duration));						
							}
							if (Integer.parseInt(distance)!=0) {
								exercise.setDistance(Float.parseFloat(distance));						
							}
							if (Integer.parseInt(calorie)!=0) {
								exercise.setCalorie(Float.parseFloat(calorie));						
							}
							
							exercise.setMaxspeed((float)(Float.parseFloat(maxspeed)/10.0));						
							
							lockdao.updateExercise(exercise);
						}else {
							Exercise exercise = new Exercise();
							exercise.setCabinId(CabinID);
							exercise.setDuration(Float.parseFloat(duration));
							exercise.setDistance(Float.parseFloat(distance));
							exercise.setCalorie(Float.parseFloat(calorie));
							exercise.setMaxspeed((float)(Float.parseFloat(maxspeed)/10.0));
							lockdao.insertExercise(exercise);
						}
					}
					
					//血压数据
					if (line.indexOf("HiBlood") != -1 || line.indexOf("LoBlood") != -1 ||
							line.indexOf("Pulse") != -1) {
						
						Blood blood = new Blood();
						blood.setCabinId(CabinID);
						if (line.indexOf("HiBlood") != -1) {
							hiblood = jsMap.get("HiBlood").toString().trim();
							blood.setSystolic(Integer.parseInt(hiblood));							
						} else {
							blood.setSystolic(0);
						}
						
						if (line.indexOf("LoBlood") != -1) {
							loblood = jsMap.get("LoBlood").toString().trim();
							blood.setDiastolic(Integer.parseInt(loblood));							
						} else {
							blood.setDiastolic(0);
						}
						
						if (line.indexOf("Pulse") != -1) {
							pulse = jsMap.get("Pulse").toString().trim();
							blood.setPulse(Integer.parseInt(pulse));						
						} else {
							blood.setPulse(0);
						}
						lockdao.insertBlood(blood);
						
					}
					
					//体重和电阻数据
					if (line.indexOf("Res") != -1 || line.indexOf("Weight") != -1 ) {
						
						Weight weight_sql = new Weight();
						weight_sql.setCabinId(CabinID);
						if (line.indexOf("Res") != -1) {
							res = jsMap.get("Res").toString().trim();						
							weight_sql.setRes((float)(Float.parseFloat(res)/10.0));
						}else{
							weight_sql.setRes(0.0f);
						}
						if (line.indexOf("Weight") != -1) {
							weight = jsMap.get("Weight").toString().trim();							
							weight_sql.setWeight((float)(Float.parseFloat(weight)/100.00));
						}else{
							weight_sql.setWeight(0.00f);
						}
						
						lockdao.insertWeight(weight_sql);
					}
					 

					CABINKEY = "CABIN:" + CabinID;

					// 向设备返回应答
//					CommonLog.debug(this.getClass(), "向通信节点返回应答：!");
//					sendCmdToLock(os, CabinID, LOCKCMD.RESPONSE.getName());

					// 车位锁认证
					if (!DevValidator.validateLock(CabinID)) {
						CommonLog.warn(this.getClass(), "CABIN注册信息不完备，没有相应处理。CABINID=" + CabinID);
						continue;
					}

					// 将os保存至MAP以便传递后台服务器命令之用 （若值已存在，则覆盖）
					 OSMap2Node.osMap.put(CABINKEY, os);

					JedisUtils.waitRedisServer(); // 等待开启Redis-Server
					jedis = JedisUtils.getJedis();
					old_door = jedis.hget(CABINKEY, "DOOR").trim(); // 获取上次收到的状态以便检查状态是否有变化
					old_light = jedis.hget(CABINKEY, "LIGHT").trim();
					old_aqi = jedis.hget(CABINKEY, "AQI").trim();
					old_temperature = jedis.hget(CABINKEY, "TEMPERATURE").trim();
					old_color = jedis.hget(CABINKEY, "COLOR").trim();
					old_emgstop = jedis.hget(CABINKEY, "EMGSTOP").trim();
					old_emgcall = jedis.hget(CABINKEY, "EMGCALL").trim();
					

					jedis.hset(CABINKEY, "DOOR", door);
					jedis.hset(CABINKEY, "AQI", aqi);
					jedis.hset(CABINKEY, "TEMP", temperature);
					jedis.hset(CABINKEY, "LIGHT", light);
					jedis.hset(CABINKEY, "COLOR", color);
					jedis.hset(CABINKEY, "EMGSTOP", emgstop);
					jedis.hset(CABINKEY, "EMGCALL", emgcall);

					jedis.close();

					// 如果状态有更新，将新状态更新到MySQL
					if (old_door==null||!door.equals(old_door)) {
						lockdao.updateLockProperties("cabins",CabinID, "door", door);
					}
					if (old_light==null||!light.equals(old_light)) {
						lockdao.updateLockProperties("cabins",CabinID, "light", light);
					}
					if (old_color==null||!color.equals(old_color)) {
						lockdao.updateLockProperties("cabins",CabinID, "color", color);
					}
					if (old_aqi==null||!aqi.equals(old_aqi)) {
						lockdao.updateLockProperties("cabins",CabinID, "aqi", aqi);
					}
					if (old_temperature==null||!temperature.equals(old_temperature)) {
						lockdao.updateLockProperties("cabins",CabinID, "temperature", temperature);
					}
					if (old_emgstop==null||!emgstop.equals(old_emgstop)) {
						lockdao.updateLockProperties("cabins",CabinID, "emergency_stop", emgstop);
					}
					if (old_emgcall==null||!emgcall.equals(old_emgcall)) {
						lockdao.updateLockProperties("cabins",CabinID, "emergency_call", emgcall);
					}
					lockdao.updateLockProperties("cabins",CabinID, "online", "1");
					
				} else {
					// CommonLog.warn.warn("收到协议外信息时，关闭该设备连接！");
					// sendCmdToLock(os, ID, LOCKCMD.HIBERNATE.getName());
					// socket.close();
					// break;
					CommonLog.warn(this.getClass(), "从通信节点收到协议外信息，不做处理。继续侦听数据......");
					continue;
				}
			} // while-loop-end
		} catch (SocketException e) {
			CommonLog.error(this.getClass(), e);
			CommonLog.info(this.getClass(), "通信节点连接超时。关闭相应的连接。");
		}catch (SocketTimeoutException e) {
			CommonLog.error(this.getClass(), e);
			CommonLog.info(this.getClass(), "通信节点连接超时。关闭相应的连接。");
		} catch (IOException e1) {
			CommonLog.warn(this.getClass(), e1);
			CommonLog.info(this.getClass(), "通信节点已离开:" + socket);
		} catch (JSONException e2) {
			CommonLog.warn(this.getClass(), e2);
			CommonLog.info(this.getClass(), "通信节点端数据格式异常。关闭相应的连接。");
		} catch (Exception e3) {
			CommonLog.warn(this.getClass(), e3);
			CommonLog.info(this.getClass(), "通信节点已离开:" + socket);
		} finally {
			if (null != jedis)
				jedis.close();

			// 关闭输入输出流
			try {
				 OSMap2Node.osMap.remove(CABINKEY);
				is.close();
				os.close();
				socket.close();
				lockdao.updateLockProperties("cabins",CabinID, "online", "0");
			} catch (Exception e) {
				CommonLog.warn(this.getClass(), e);
			}
		}
	}

	/*
	 * 向车位锁发送指令
	 */
	private void sendCmdToLock(PrintWriter os, String cabinid, String cmd) {
		os.print(cabinid + "," + cmd);
		os.flush();
	}

}