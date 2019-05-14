package com.babi.lockservice;

/**
 * 
 * @createBy 文慧
 * @Date 2018年3月19日下午2:10:04
 * @description LockCurrentlyStatus.java获取锁的及时状态
 * @changeBy 文慧
 * @Date 2018年3月19日下午2:10:04
 * @description
 */
public class LockCurrentStatus {

	private int status;

	public LockCurrentStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 * @createBy 文慧
	 * @Date 2018年3月22日下午2:29:51
	 * @description isClose 车锁是否处于关闭状态
	 *
	 */
	public boolean isClose() {
		int result = status & 2;
		if (result == 2) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @createBy 文慧
	 * @Date 2018年3月22日下午2:39:29
	 * @description isOpen 车锁是否处于开启状态
	 *
	 */
	public boolean isOpen() {
		int result = status & 4;
		if (result == 4) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @createBy 文慧
	 * @Date 2018年3月22日下午2:41:29
	 * @description isSnatch 车锁是否欠时
	 *
	 */
	public boolean isSnatch() {
		int result = status & 64;
		if (result == 64) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @createBy 文慧
	 * @Date 2018年3月22日下午2:43:21
	 * @description isLowVoltage 车锁是否电量低
	 *
	 */
	public boolean isLowVoltage() {
		int result = status & 1;
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @createBy 文慧
	 * @Date 2018年3月22日下午2:46:03
	 * @description isOccupied 车位锁是否被占用
	 *
	 */
	public boolean isOccupied() {
		int result = status & 8;
		if (result == 8) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @createBy 文慧
	 * @Date 2018年3月22日下午2:48:02
	 * @description isOverCurrent 车位锁是否堵转
	 *
	 */
	public boolean isOverCurrent() {
		int result = status & 16;
		if (result == 16) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @createBy 文慧
	 * @Date 2018年3月22日下午2:55:38
	 * @description isOvertime 是否超时
	 *
	 */
	public boolean isOvertime() {
		int result = status & 32;
		if (result == 32) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		LockCurrentStatus lock = new LockCurrentStatus(66);
		System.out.println("车锁是否处于上升的状态" + lock.isClose());
		System.out.println("车锁是否处于下降的状态" + lock.isOpen());
		System.out.println("车锁是否欠时" + lock.isSnatch());
		System.out.println("是否超时" + lock.isOvertime());
		System.out.println("是否低电量" + lock.isLowVoltage());
		System.out.println("是否堵转" + lock.isOverCurrent());
	}

}
