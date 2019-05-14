package com.babi.common.utils;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/*
 * 作用于向TCP端发送开关锁的命令
 * 
 */
public class QMSender {

	// 标明一个消息队列。
	private static final String MESSAGE_QUEUE = "cabin-mq";

	// 默认的ActiveMQ服务器端绑定的端口。
	private static final int PORT = 61616;

	// 发送10条消息。
	// private static final int COUNT = 10;

	public static void send(String state, String deviceID) {
		// ConnectionFactory ：连接工厂，JMS 创建连接
		ConnectionFactory connectionFactory;

		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;

		// Session： 一个发送或接收消息的线程
		Session session;

		// Destination ：消息的目的地
		Destination destination;

		// MessageProducer：消息生产者。
		MessageProducer producer;

		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:" + PORT);

		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();

			// 启动
			connection.start();

			// 获取操作连接
			session = connection.createSession(Boolean.TRUE.booleanValue(), Session.AUTO_ACKNOWLEDGE);

			// 获取session注意参数值 liwenhui 是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue(MESSAGE_QUEUE);

			// 得到消息生成者，发送者
			producer = session.createProducer(destination);

			// 设置不持久化
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// 发送消息。
			sendMessage(session, producer, state, deviceID);

			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}

	public static void sendMessage(Session session, MessageProducer producer, String state, String deviceID) {
		MapMessage message = null;
		try {
			message = session.createMapMessage();
			message.setString("state", state);
			message.setString("deviceID", deviceID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 发送消息到目的地方
		try {
			//System.err.println(message);
			System.out.println(message);
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
