package com.babi.lockservice;

/**
 * BABI服务器指令类
 * @author	LiWenhui
 * @Date	2018.03.19
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public enum SERVERCMD {
	LOCKDOWN("开启车位锁指令", 0), LOCKUP("关闭车位锁指令", 1), LOCKHIBERNATE("休眠命令", 2), LOCKRING("响铃信号", 3);

	private String name;
	private int index;
	
	private SERVERCMD(String name, int index) {
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
