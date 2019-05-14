package com.babi.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件类
 * @author	GuoLiangyun
 * @Date	2018.05.24
 * 
 */
@Component
public class Params {

	public static int SOCKET_PORT = 0;			// socket端口
	public static int TIMEOUT = 0;				// 超时时间
	public static String MESSAGE_TOPIC = null;	// MQTopic名字
	public static String MQ_HOST = null;		// MQ Host
	public static int MQ_PORT = 0;				// MQ端口
	public static String MQ_NAME = null;		// MQ 名称
	public static int MQ_COUNT = 0;				// MQ 数量
	public static int PATROL_PERIOD = 24;		// 车锁状态巡检周期
	public static int PATROL_STARTH = 22;			// 车锁状态巡检开始时间（PATROL_STARTH:00:00）
	public static String DB_DRIVER = null;
	public static String DB_URL = null;
	public static String DB_USERNAME = null;
	public static String DB_PASSWORD = null;

	// 基于配置文件初始化参数
	@Value("${SOCKET_PORT}")
	public void setSOCKET_PORT(String param) {
		Params.SOCKET_PORT = Integer.parseInt(param);
	}

	@Value("${TIMEOUT}")
	public void setTIMEOUT(String param) {
		Params.TIMEOUT = Integer.parseInt(param);
	}
	
	@Value("${MESSAGE_TOPIC}")
	public void setMESSAGE_TOPIC(String param) {
		Params.MESSAGE_TOPIC = param;
	}
	
	@Value("${MQ_HOST}")
	public void setMQ_HOST(String param) {
		Params.MQ_HOST = param;
	}
	
	@Value("${MQ_PORT}")
	public void setMQ_PORT(String param) {
		Params.MQ_PORT = Integer.parseInt(param);
	}
	
	@Value("${MQ_NAME}")
	public void setMQ_NAME(String param) {
		Params.MQ_NAME = param;
	}
	
	@Value("${MQ_COUNT}")
	public void setMQ_COUNT(String param) {
		Params.MQ_COUNT = Integer.parseInt(param);
	}
	
	@Value("${PATROL_PERIOD}")
	public void setPATROL_PERIOD(String param) {
		Params.PATROL_PERIOD = Integer.parseInt(param);
	}
	
	@Value("${PATROL_STARTH}")
	public void setPATROL_STARTH(String param) {
		Params.PATROL_STARTH = Integer.parseInt(param);
	}
	
	@Value("${DB_DRIVER}")
	public void setDB_DRIVER(String param) {
		Params.DB_DRIVER = param;
	}
	
	@Value("${DB_URL}")
	public void setDB_URL(String param) {
		Params.DB_URL = param;
	}
	
	@Value("${DB_USERNAME}")
	public void setDB_USERNAME(String param) {
		Params.DB_USERNAME = param;
	}
	
	@Value("${DB_PASSWORD}")
	public void setDB_PASSWORD(String param) {
		Params.DB_PASSWORD = param;
	}


	/*
	 * 显示获取的配置文件参数值
	 */
	public static void echoProperties() {   
		CommonLog.info(Params.class, "SOCKET_PORT:" + SOCKET_PORT);
		CommonLog.info(Params.class, "TIMEOUT:" + TIMEOUT);
		CommonLog.info(Params.class, "MESSAGE_TOPIC:" + MESSAGE_TOPIC);
		CommonLog.info(Params.class, "MQ_HOST:" + MQ_HOST);
		CommonLog.info(Params.class, "MQ_PORT:" + MQ_PORT);
		CommonLog.info(Params.class, "MQ_NAME:" + MQ_NAME);
		CommonLog.info(Params.class, "MQ_COUNT:" + MQ_COUNT);
		CommonLog.info(Params.class, "PATROL_PERIOD:" + PATROL_PERIOD);
		CommonLog.info(Params.class, "PATROL_STARTH:" + PATROL_STARTH);
		CommonLog.info(Params.class, "DB_DRIVER:" + DB_DRIVER);
		CommonLog.info(Params.class, "DB_URL:" + DB_URL);
		CommonLog.info(Params.class, "DB_USERNAME:" + DB_USERNAME);
		CommonLog.info(Params.class, "DB_PASSWORD:" + DB_PASSWORD);
	} 

}
