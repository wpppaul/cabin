package com.babi.lockservice;

import java.util.*;

import com.babi.common.CommonLog;
import com.babi.common.Params;

/**
 * MQ守护线程
 * @author GuoLiangyun
 * @Date	2018.03.23
 */
public class QMConsumerDaemon {
    private final int seconds; // 巡检周期
	private int nMqCount = Params.MQ_COUNT; // MQ数量
	private List<QMConsumer> consumerList = new ArrayList<QMConsumer>();

    /*
     * 构造方法
     */
    public QMConsumerDaemon(int scnd) {
        this.seconds = scnd;

        startMQThreads();
    }  

	/*
	 * 启动所有MQ消息接收线程
	 */
	public void startMQThreads() {
		if (1 == nMqCount) {
			newConsumerThread(Params.MQ_NAME);
		} else {
			// 启动所有MQ消息接收线程
			for (int i = 0; i < nMqCount; i++) {
				String sMQ_NAME = Params.MQ_NAME + String.valueOf(i + 1);

				// 生成并启动该MQ消息接收线程
				newConsumerThread(sMQ_NAME);
			}
		}
	}

	/*
	 * 生成并启动指定MQ消息接收线程
	 */
	private void newConsumerThread(String mq_name) {
		QMConsumer consumer = new QMConsumer(mq_name);
		consumerList.add(consumer);
		CommonLog.info(this.getClass(), "启动MQ<" + mq_name + ">的消息接收线程......");
	}

	/*
	 * 管理所有MQ线程的连接状态。控制终止线程重新连接。
	 */
    public void startTimer() {
    	TimerTask task = new TimerTask() {
            public void run() {  
    			Iterator<QMConsumer> it = consumerList.iterator();
    			while (it.hasNext()) {
    				QMConsumer consumer = (QMConsumer) it.next();
    				boolean bRunning = consumer.getRunning();
    				if (!bRunning) {
    					CommonLog.warn(this.getClass(), "尝试连接MQ服务端<" + consumer.getQName() + ">......");
    					consumer.reStartProc();
    				}
    			}
            }
    	};
    	
    	// 延迟重复执行
    	Timer timer = new Timer();
        timer.schedule(task, 5000L, 1000*seconds);  
    }

}
