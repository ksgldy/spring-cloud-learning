package cn.idea360.oracle.dto;

import java.util.ArrayList;
import java.util.List;

public class PageRespDTO<T> {

    // 总页数
    private Integer pages = 0;
    // 当前页条数
    private Integer size = 10;
    // 总条数
    private Integer total = 0;
    // 当前页码
    private Integer pageNum = 1;
    // 数据
    private List<T> records = new ArrayList<>();



    public Integer getPages() {

        return (int)Math.ceil((double)total/size);
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
