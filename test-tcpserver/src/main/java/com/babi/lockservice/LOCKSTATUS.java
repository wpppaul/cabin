package com.babi.lockservice;

/**
 * 车锁状态类
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public enum LOCKSTATUS {

	STOCK("在库", 1), INUSE("在用", 2), MALFUNCTION("故障中", 3), DISCARDED("已报废", 4), LOWBATTERY("电量低", 5), PATROLFAIL("巡检无应答", 5);

	private String name;
	private int index;

	private LOCKSTATUS(String name, int index) {
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
