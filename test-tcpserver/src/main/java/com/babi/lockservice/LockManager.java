package com.babi.lockservice;

import java.net.ServerSocket;
import java.net.Socket;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.babi.common.CommonLog;
import com.babi.common.Params;
import com.babi.dao.JedisUtils;

/**
 * 地锁管理器类（服务器端）
 * @author	LiWenhui
 * @Date	2018.03.19
 * @author	GuoLiangyun
 * @Date	2018.03.23
 * @Date	2018.05.31
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.babi.lockservice" })
//@EnableAsync
public class LockManager {
	static ServerSocket serverSocket = null;

	/*
	 * 构造方法
	 */
	public LockManager() {
		super();
	}
	public LockManager(String[] args) {
		super();
	}

//	/*
//	 * 主程序入口
//	 */
//	public static void main(String[] args) {
//
//		// 启动LockManager
//		LockManager lockmanager = new LockManager(args);
//		try {
//			lockmanager.startProcess();
//		} catch (Exception e) {
//			CommonLog.error(LockManager.class, e);
//		}
//	}

	/*
	 * 启动处理
	 */
	public void startProcess() throws Exception {

		CommonLog.info(this.getClass(), "**************************************************");
		CommonLog.info(this.getClass(), "LOCK服务器开始启动......");

		// 显示配置的参数值
		Params.echoProperties();

		// 加载LOCK数据进入Redis缓存
		DevValidator.loadLockToRedis();
		
		// 启动MQ
		QMConsumerDaemon qDaemon = new QMConsumerDaemon(10);
		qDaemon.startTimer();

		// 启动设备状态巡检
//		LockPatrolScheduler patrolTimer = new LockPatrolScheduler(Params.PATROL_PERIOD, Params.PATROL_STARTH);
//		patrolTimer.startTimer();

		// 开放Socket服务端供设备连接
		try {
			serverSocket = new ServerSocket(Params.SOCKET_PORT);
		} catch (Exception e) {
			CommonLog.error(this.getClass(), e);
			System.exit(1);
		}

		while (true) {
			Socket socket = null;
			LockThread lockThread = null;

			// 使用accept()等待客户通信,接受用户的socket对象;在调用方法前处于阻断的状态
			try {
				socket = serverSocket.accept();
			} catch (Exception e) {
				CommonLog.error(this.getClass(), e);
			}

			// 进入线程方法，用来获取ID，存放Socket
			try {
				lockThread = new LockThread(socket);
				lockThread.start();
			} catch (Exception e) {
				CommonLog.error(this.getClass(), e);
			}

		}
	}


}
