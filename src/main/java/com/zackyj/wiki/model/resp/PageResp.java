package com.zackyj.wiki.model.resp;

import java.util.List;

public class PageResp<T> {
    private long total;
    private List<T> list;


    public PageResp(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                       "total=" + total +
                       ", list=" + list +
                       '}';
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
