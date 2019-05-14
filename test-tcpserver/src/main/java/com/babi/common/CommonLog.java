package com.babi.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log公共类
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public class CommonLog {
    //定义一个全局的记录器，通过LoggerFactory获取
    //public final static Logger logger = LoggerFactory.getLogger(CommonLog.class);

	public static boolean isError = LoggerFactory.getLogger(CommonLog.class).isErrorEnabled();
	public static boolean isWarn = LoggerFactory.getLogger(CommonLog.class).isWarnEnabled();
	public static boolean isInfo = LoggerFactory.getLogger(CommonLog.class).isInfoEnabled();
	public static boolean isDebug = LoggerFactory.getLogger(CommonLog.class).isDebugEnabled();
	public static boolean isTrace = LoggerFactory.getLogger(CommonLog.class).isTraceEnabled();

	/**
	 * Error 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void error(Class<? extends Object> clazz, String message){
		if(!isError) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(message);
	}

	/**
	 * Error 输出
	 * @param clazz  	目标.Class
	 * @param e			例外Object
	 */
	public static void error(Class<? extends Object> clazz, Exception e){
		if(!isError) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(stackTrace(e));
	}

	/**
	 * Warn 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void warn(Class<? extends Object> clazz ,String message){
		if(!isWarn) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.warn(message);
	}

	/**
	 * Warn 输出
	 * @param clazz  	目标.Class
	 * @param e			例外Object
	 */
	public static void warn(Class<? extends Object> clazz, Exception e){
		if(!isWarn) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.warn(stackTrace(e));
	}

	/**
	 * Info 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void info(Class<? extends Object> clazz, String message){
		if(!isInfo) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.info(message);
	}

	/**
	 * Info 输出
	 * @param clazz  	目标.Class
	 * @param e			例外Object
	 */
	public static void info(Class<? extends Object> clazz, Exception e){
		if(!isInfo) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.info(stackTrace(e));
	}

	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void debug(Class<? extends Object> clazz, String message){
		if(!isDebug) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.debug(message);
	}

	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param e			例外Object
	 */
	public static void debug(Class<? extends Object> clazz, Exception e){
		if(!isDebug) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.debug(stackTrace(e));
	}

	/**
	 * Trace 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void trace(Class<? extends Object> clazz ,String message){
		if(!isTrace) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.trace(message);
	}

	/**
	 * Trace 输出
	 * @param clazz  	目标.Class
	 * @param e			例外Object
	 */
	public static void trace(Class<? extends Object> clazz, Exception e){
		if(!isTrace) return ;
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.trace(stackTrace(e));
	}

	/*
	 * 
	 */
    private static String stackTrace(Exception e) {  
        try {  
            StringWriter sw = new StringWriter();  
            PrintWriter pw = new PrintWriter(sw);  
            e.printStackTrace(pw);  
            return "\r\n" + sw.toString() + "\r\n";  
        } catch (Exception e2) {  
            return "Bad exception trace contents: " + e2.toString();  
        }  
    } 

}
