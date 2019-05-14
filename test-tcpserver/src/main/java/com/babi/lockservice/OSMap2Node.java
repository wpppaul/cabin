package com.babi.lockservice;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 至通信节点的输出流集合类
 * @author	LiWenhui
 * @Date	2018.03.19
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public class OSMap2Node {

	// 此Map用来存放socket的输出流 <ModuleID, os>
	public static Map<String, PrintWriter> osMap = new HashMap<String, PrintWriter>();

}
