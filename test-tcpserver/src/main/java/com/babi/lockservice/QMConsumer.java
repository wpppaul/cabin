package com.babi.lockservice;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.alibaba.fastjson.JSON;
import com.babi.common.CommonLog;
import com.babi.common.Params;

/**
 * 消息消费类
 * @author	LiWenhui
 * @Date	2018.03.19
 * @author	GuoLiangyun
 * @Date	2018.03.23
 * @author	GuoLiangyun
 * @Date	2018.05.23
 */
public class QMConsumer implements Runnable {
	private ConnectionFactory connectionFactory = null; // 连接工厂
	private Connection connection = null; // 连接
	private Session session = null; // 会话：接受或者发送消息的线程
	private Destination destination = null; // 消息的目的地
	private MessageConsumer consumer = null; // 消息接收者
	private String m_qName = null;

	// my thread
	private Thread m_thread = null;
	private boolean m_bRun = true;

	/*
	 * Constructor
	 */
	public QMConsumer(String qname) {
		CommonLog.info(this.getClass(), "MQ启动中......");
		m_qName = qname;
		startProc();
	}

	/*
	 * 
	 */
	private void startProc() {
		m_bRun = true;

		// Start thread
		if (null == m_thread) {
			m_thread = new Thread(this);
		}
		m_thread.start();

	}

	/**
	 * Restart the application
	 */
	public void reStartProc() {
		// Stop my thread
		m_bRun = false;
		m_thread = null;

		// Start my thread
		startProc();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		// 实例化连接工厂
		try {
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, Params.MQ_HOST + ":" + Params.MQ_PORT);

			// JMS客户端通过连接工厂创建到JMS Provider的连接, 启动
			connection = connectionFactory.createConnection();
			connection.start();

			// 建立一个发送或接收消息的线程
			session = connection.createSession(Boolean.FALSE.booleanValue(), Session.AUTO_ACKNOWLEDGE);

			// 获取session中命名消息队列
			destination = session.createQueue(m_qName);

			// 生成消息接收者
			consumer = session.createConsumer(destination);

			while (m_bRun) {
				// 无时间参数表示一直等待，直到收到消息。
				// 有时间参数表示指定时间后没有消息则结束时，如果存在消息就在取完消息后结束
				MapMessage message = (MapMessage) consumer.receive();
				String name = message.getString("state");
				String CABIN = message.getString("deviceID");
				String CABINKEY = name;
				CommonLog.info(this.getClass(), "来自API的消息:"+name+":"+CABIN);	
				// 处理MQ发来的消息
				if (null != name && null != CABIN) {
					
					PrintWriter os = OSMap2Node.osMap.get(CABINKEY);
					if (os != null) {// 判断是否存在此向该设备的输出流
						sendCmdToLock(os,CABIN);
					}
				}
			}
		} catch (Exception e) {
			CommonLog.error(this.getClass(), e.getMessage());
		} finally {
			m_bRun = false;
			m_thread = null;
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}

	public boolean getRunning() {
		return this.m_bRun;
	}

	public String getQName() {
		return m_qName;
	}
	

	/*
	 * 向车位锁发送指令
	 */
	private void sendCmdToLock(PrintWriter os, String cabin) {
		os.print(cabin);
		os.flush();
	}

//	private String getIMEIfromRedis(String lockid) throws Exception {
//		Jedis jedis = JedisUtils.getJedis();
//		String strIMEI = jedis.hget("CABIN:"+lockid, "IMEI"); // 保存上次收到的状态以便检查状态是否有变化
//		jedis.close();
//	
//		return strIMEI;
//	}
	
}
