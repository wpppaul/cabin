package com.babi.lockservice;

/**
 * LOCK服务器向车位锁发送指令类
 * @author	GuoLiangyun
 * @Date	2018.05.20
 */
public enum LOCKCMD {
	QUERYLOCKSTATUS("@\r\n", 0), CLOSELOCK("#\r\n", 1), OPENCLOCK("*\r\n", 2), RESPONSE("!\r\n", 3), HIBERNATE("%\r\n", 4);

	private String name;
	private int index;
	
	private LOCKCMD(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
