package com.babi.common.beans;

import java.util.List;

/**
 * 
 * 表格数据传输对象
 * 
 * @param <T>
 */
public class GridData<T> {
	private long total;
	private List<T> res;

	public GridData(long total, List<T> res) {
		super();
		this.total = total;
		this.res = res;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRes() {
		return res;
	}

	public void setRes(List<T> res) {
		this.res = res;
	}

}
