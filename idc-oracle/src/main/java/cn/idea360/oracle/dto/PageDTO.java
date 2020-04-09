package cn.idea360.oracle.dto;

public class PageDTO {

    private Integer page;
    private Integer size;
    private Integer beginResult;
    private Integer endResult;
    private String keyword;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getBeginResult() {
        return beginResult;
    }

    public void setBeginResult(Integer beginResult) {
        this.beginResult = beginResult;
    }

    public Integer getEndResult() {
        return endResult;
    }

    public void setEndResult(Integer endResult) {
        this.endResult = endResult;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
