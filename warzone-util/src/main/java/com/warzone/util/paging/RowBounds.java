package com.warzone.util.paging;

public class RowBounds {
    public final static int NO_ROW_PAGE_NUM = 1;
    public final static int NO_ROW_PAGE_SIZE = Integer.MAX_VALUE;
    public final static RowBounds DEFAULT = new RowBounds();

    private int pageNum;
    private int pageSize;

    public RowBounds() {
        this.pageNum = NO_ROW_PAGE_NUM;
        this.pageSize = NO_ROW_PAGE_SIZE;
    }

    public RowBounds(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
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


	@Override
	public String toString() {
		return "RowBounds [pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}

	public int getOffset(){
		return (this.pageNum-1)*pageSize;
	}
	public int getLimit(){
		return this.pageSize;
	}
}
