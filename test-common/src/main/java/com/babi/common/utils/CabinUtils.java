package com.babi.common.utils;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.babi.commom.dto.StatusDto;

import redis.clients.jedis.Jedis;

@Component
public class CabinUtils {

	static RestTemplate restTemplate = new RestTemplate();

	// 设备锁消息存储的缓冲区类型（Map,Redis）
	private static String LOCK_BUFFER = "Redis";
	// 开锁消息代码
	private static String NAME = "CABIN";
	// 关锁消息代码
//	private static String CLOSE_CODE = "0";
//	// 休眠
//	private static String HIBERNATE_CODE = "2";
//	// 呼叫消息代码
//	private static String CALL_CODE = "3";
	// 操作超时时间
	private static long LOCK_OPERATION_TIMR_OUT = 1000 * 1L;

//	@Value("${common.lockBuffer}")
//	public void setLockBuffer(String lockBuffer) {
//		CabinUtils.LOCK_BUFFER = lockBuffer;
//	}

	// 从缓存获取锁及其状态
	private static Object findLockFromBuffer(String cabinId) throws Exception {
		if ("Redis".equals(LOCK_BUFFER)) {
			return findLockFromRedis(cabinId);
		} else {
			throw new Exception("未找到缓冲区！");
		}
	}


	// 从Redis缓存获取锁及其状态
	public static String findLockFromRedis(String cabinId) {
		Jedis jedis = JedisUtils.getJedis();
		String status = jedis.hget("CABIN:" + cabinId, "DOOR");
		jedis.close();
		return status;
	}

	// 开锁
	public static boolean sendmessage(StatusDto cabin) {
		System.out.println(cabin.getCabinid());
		HashMap<String,Object> map =new HashMap<>();
		try {
			if (findLockFromBuffer(cabin.getCabinid()) != null) {
				String CABINKEY = "CABIN:"+cabin.getCabinid();
				map.put("Door", cabin.getDoor());
				map.put("Temp", cabin.getTemperature()==null?null:cabin.getTemperature().toString());
				map.put("Color", cabin.getColor());
				map.put("Due", cabin.getMessageDue());
				map.put("Speed", cabin.getSpeed());
				map.put("Period", cabin.getPeriod());
				map.put("Time", cabin.getTime());
				QMSender.send(CABINKEY, JSON.toJSONString(map));	
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	// 关锁
//	public static boolean closeLock(String cabinId) {
//		try {
//			if (findLockFromBuffer(cabinId) != null) {
//				QMSender.send(CLOSE_CODE, cabinId);
//				return !isTimeOutOfLockOperation(cabinId, CLOSE_CODE);
//			}
//			return false;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

	// 休眠
//	public static boolean hibernateLock(String lockId) throws Exception {
//		if (findLockFromBuffer(lockId) != null) {
//			QMSender.send(HIBERNATE_CODE, lockId);
//		}
//		return false;
//	}

	// // 通过短信打开车锁 3.24增加
	// public static boolean openLockBySms(String lockId, String mobile) {
	// new MakeLockActivate().smsSendDown(mobile);
	// return !isTimeOutOfLockOperation(lockId, OPEN_CODE);
	// }

	// // 通过短信关闭车锁 3.24增加
	// public static boolean closeLockBySms(String lockId, String mobile) {
	// new MakeLockActivate().smsSendUp(mobile);
	// return !isTimeOutOfLockOperation(lockId, CALL_CODE);
	// }

	// 呼叫锁
//	public static boolean findLock(String lockId) throws Exception {
//		if (findLockFromBuffer(lockId) != null) {
//			QMSender.send(CALL_CODE, lockId);
//			return true;
//		}
//		return false;
//	}

	// 判断操作设备锁是否超时
//	private static boolean isTimeOutOfLockOperation(String cabinId, String status) {
//		if ("Redis".equals(LOCK_BUFFER)) {
//			return isTimeOutOfLockOperation_fromRedis(cabinId, status);
//		}
//		return false;
//	}

	// 从Redis 判断操作设备锁是否超时
//	private static boolean isTimeOutOfLockOperation_fromRedis(String cabinId, String status) {
//		int statusInt = Integer.parseInt(status);
//		long startTime = System.currentTimeMillis();
//		while (true) {
//			int result = 10;
//			if (findLockFromRedis(cabinId) != null) {
//				if (new LockCurrentStatus(Integer.parseInt(findLockFromRedis(cabinId).toString())).isOpen()) {
//					result = 0;
//				} else if (new LockCurrentStatus(Integer.parseInt(findLockFromRedis(cabinId).toString())).isClose()) {
//					result = 1;
//				}
//			}
//			// 状态未改变
//			if (statusInt != result) {
//			    long endTime = System.currentTimeMillis();
//				if (endTime - startTime > LOCK_OPERATION_TIMR_OUT) {
//					return true;
//				}
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				continue;
//			} else {
//				return false;
//			}
//		}
//	}
}
