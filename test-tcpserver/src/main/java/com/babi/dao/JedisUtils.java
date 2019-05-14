package com.babi.dao;

import com.babi.common.CommonLog;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Jedis连接类
 * 
 * @author LiWenhui
 * @Date 2018.03.19
 * @author GuoLiangyun
 * @Date 2018.06.01
 */
public class JedisUtils {

	// 1、定义一个连接池对象
	private final static JedisPool POOL;

	static {
		// 初始化操作
		// 1、设置连接池的配置对象
		// JedisPoolConfig config = new JedisPoolConfig();

		JedisPoolConfig config = new JedisPoolConfig();

		// 设置池中最大连接数【可选】
		config.setMaxTotal(200);
		
		// 设置空闲时池中保有的最大连接数【可选】
		config.setMaxIdle(80);
		
		// 2、设置连接池对象
		POOL = new JedisPool(config, "localhost", 6379);
	}

	/**
	 * 从池中获取链接
	 */
	public static Jedis getJedis() throws Exception {
		try {
			return POOL.getResource();
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * 等待Redis-Server启动，直至启动为止。
	 */
	public static void waitRedisServer() {
		Jedis jedis = null;
		while (true) {
			try {
				jedis = getJedis();
				break;
			} catch (JedisConnectionException e) {
				CommonLog.error(JedisUtils.class, "Jedis连接失败！ 请启动Redis-Server。");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ie) {
				}
				continue;
			} catch (Exception e) {
				CommonLog.error(JedisUtils.class, e);
			}
		}
		jedis.close();
	}

}
