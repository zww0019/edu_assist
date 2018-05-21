package com.warzone.util.paging;

import java.util.ArrayList;
import java.util.List;

public class Pager<T>{
    private int pageNum;
    private int pageSize;
    private int total;
    private List<T> datas=new ArrayList<T>();

    public Pager() {
    }

    public Pager(int pageNum, int pageSize, int total, List<T> datas) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.datas = datas;
    }


    public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

	@Override
	public String toString() {
		return "Pager [pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", datas=" + datas + "]";
	}

   
}
