package com.babi.dao;

import redis.clients.jedis.Jedis;

public class testRedis {

	public static void main(String[] args) {
		LockDAO lockdao = new LockDAO();

		// 初始化数据进入redis

		try {
			Jedis jedis = JedisUtils.getJedis();
			System.out.println(jedis.hget("locklist", "3465039972683:863465039972682") == null);

			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
