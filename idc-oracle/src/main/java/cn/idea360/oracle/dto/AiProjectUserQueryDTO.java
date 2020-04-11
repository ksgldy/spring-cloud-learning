package cn.idea360.oracle.dto;

public class AiProjectUserQueryDTO {

    private Integer departmentId; // 部门id
    private String searchField; // 过滤类型: userId|realName
    private String keyword; // 查询关键词


    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
