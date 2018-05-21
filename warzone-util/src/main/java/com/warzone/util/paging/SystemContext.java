package com.warzone.util.paging;


public class SystemContext {
    private static ThreadLocal<RowBounds> rowBounds=new ThreadLocal<RowBounds>();
    private static final int DEFAULT_PAGE_NUM=1;
    private static final int DEFAULT_PAGE_SIZE=10;
    public static void setRowBounds(RowBounds _RowBounds){
        rowBounds.set(_RowBounds);
    }
    public static RowBounds getRowBounds(){
        RowBounds ret= rowBounds.get();
        if(ret==null){
            ret=new RowBounds(DEFAULT_PAGE_NUM,DEFAULT_PAGE_SIZE);
        }
        return ret;
    }
    public static void removeRowBounds(){
        rowBounds.remove();
    }
}
