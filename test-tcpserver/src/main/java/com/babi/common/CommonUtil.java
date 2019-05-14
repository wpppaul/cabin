package com.babi.common;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.babi.lockservice.CarportLock;

/**
 * 公共工具类
 * @author	LiWenhui
 * @Date	2018.03.19
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public class CommonUtil {
	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}

	/**
	 * 对操作硬件的返回值进行处理 直接以十进制的方法存放错误信息；
	 * 
	 * @param line
	 */
	public Map<String, Object> Processingreturnvalue(String line, Socket socket, CarportLock carportLock) {
		// 对状态进行统一的处理
		Map<String, Object> map = new HashMap<String, Object>();
//		String ID = line.substring(line.indexOf("ID:") + 4, line.indexOf("ID:") + 19);
//		int result = Integer.parseInt(line.substring(line.indexOf("STATE:") + 6, line.indexOf("}")));
//		// String STATE = "0";
//		// Sender sender = new Sender();
//		// 如果是对状态的返回值,获取结果，并将得出结果的值解析，反馈到前端
//		int close = result & 2; // "close" 车锁处于上升的位置
//		int open = result & 4;// "open" 车锁处于下降的位置
//		int snatch = result & 64;// 欠时
//		int lowvoltage = result & 1;// 电量 "lowvoltage":低电压
//		int occupied = result & 8;// "occupied" 车位上方是否有车
//		int overCurrent = result & 16;// 堵转 "overCurrent"
//		int overtime = result & 32;// 超时
//		if (close == 2) {
//			// STATE = "1";// 车位时升起状态
//			// sender.sender(STATE, ID);
//		} else if (open == 4) {
//			// STATE = "0";// 车位降下状态
//			// sender.sender(STATE, ID);
//		}
//		if (snatch == 64) {
//			CommonLog.info(CommonUtil.class, "欠时");
//		}
//		if (lowvoltage == 1) {
//			CommonLog.info(this.getClass(), "电量低");
//		}
//		if (occupied == 8) {
//			CommonLog.info(this.getClass(), "车位上方有车");
//		}
//		if (overCurrent == 16) {
//			CommonLog.info(this.getClass(), "堵转");
//		}
//		if (overtime == 32) {
//			CommonLog.info(this.getClass(), "超时");
//		}
//		carportLock.setID(ID);
//		carportLock.setSTATE(result);
//		carportLock.setLowvoltage(result & 1);
//		carportLock.setOccupied(result & 8);
//		carportLock.setOpen(open);
//		carportLock.setClose(close);
//		carportLock.setOverCurrent(result & 16);
//		carportLock.setOverTime(result & 32);
//		carportLock.setSnatch(result & 64);
//		map.put("ID", ID);
		map.put("result", carportLock);
		return map;
	}
}
