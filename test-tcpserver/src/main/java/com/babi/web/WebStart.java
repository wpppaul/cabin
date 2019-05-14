package com.babi.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.babi.common.CommonLog;
import com.babi.lockservice.LockManager;

/**
 * 
 * @author	LiWenhui
 * @Date	2018.05.20
 * @author	GuoLiangyun
 * @Date	2018.05.23
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.babi.common" })
public class WebStart {

	public static void main(String args[]) {

		SpringApplication.run(WebStart.class, args);

		// 启动LockManager
		LockManager lockmanager = new LockManager();
		try {
			lockmanager.startProcess();
		} catch (Exception e) {
			CommonLog.error(LockManager.class, e);
		}
	}

}
