package com.babi.common.utils;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	// 1、定义一个连接池对象
	private final static JedisPool POOL;

	static {
		// 初始化操作
		// 1、设置连接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();

		// 设置池中最大连接数【可选】
		config.setMaxTotal(50);
		// 设置空闲时池中保有的最大连接数【可选】
		config.setMaxIdle(10);
		// 2、设置连接池对象
		POOL = new JedisPool(config, "localhost", 6379);
	}

	/**
	 * 从池中获取链接
	 */
	public static Jedis getJedis() {
		return POOL.getResource();
	}

	public static Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	public static Long hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static Long hdel(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hdel(key, field);
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}

	public static String setex(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.setex(key, seconds, value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

}
