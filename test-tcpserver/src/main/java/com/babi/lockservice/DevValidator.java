package com.babi.lockservice;

import java.util.Map;

import com.babi.common.CommonLog;
import com.babi.dao.JedisUtils;
import com.babi.dao.LockDAO;

import redis.clients.jedis.Jedis;

/**
 * 设备验证类 （基于LOCK数据库） （注：1.替代原有车锁认证类 2.认证对象设备：车锁及通信节点）
 * 
 * @author GuoLiangyun
 * @Date 2018.05.20
 * @Date 2018.06.01
 */
public class DevValidator {
	static LockDAO lockdao = new LockDAO();

	// 加载LOCK数据进入Redis缓存
	public static void loadLockToRedis() {
		// 等待开启Redis-Server
		JedisUtils.waitRedisServer();
		
		// 加载数据
		try {
			lockdao.loadLockList();
		} catch (Exception e) {
			CommonLog.error(DevValidator.class, e);
		}
	}

	// 加载LOCK和IMEI数据进入Redis缓存
	public static void loadImeiLockToRedis() {
		// 等待开启Redis-Server
		JedisUtils.waitRedisServer();
		
		// 加载数据
		try {
//			lockdao.loadImeiList();
			lockdao.loadLockList();
		} catch (Exception e) {
			CommonLog.error(DevValidator.class, e);
		}
	}

	/*
	 * 认证车锁的合法性（判断车位锁注册信息是否完备：车位锁是否注册，并且车位锁与通信模块是否匹配）
	 */
	public static synchronized boolean validateLock(String cabinid) throws Exception {
		Jedis jedis = JedisUtils.getJedis();
		Boolean b = jedis.hexists("CABIN:"+cabinid, "DOOR");
		jedis.close();
		
		if (!b) {
			lockdao.reLoadLockToList(cabinid);

			jedis = JedisUtils.getJedis();
			b = jedis.hexists("CABIN:"+cabinid, "DOOR");
			jedis.close();
		}
		return b;
	}

}
