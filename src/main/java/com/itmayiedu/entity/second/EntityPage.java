package com.itmayiedu.entity.second;

import java.util.List;

public class EntityPage<T> {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer pageSum;
    private String searchName = "";
    private List<T> ts;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSum() {
        return pageSum;
    }

    public void setPageSum(Integer pageSum) {
        this.pageSum = pageSum;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public List<T> getTs() {
        return ts;
    }

    public void setTs(List<T> ts) {
        this.ts = ts;
    }
}
