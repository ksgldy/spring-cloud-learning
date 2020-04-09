package cn.idea360.oracle.dto;

public class PageDTO {

    private Integer page = 1;
    private Integer size = 10;
    private Integer startIndex = 1;
    private Integer endIndex = 10;
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

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

    public Integer getStartIndex() {
        return (page-1)*size+1;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return page*size;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }
}
