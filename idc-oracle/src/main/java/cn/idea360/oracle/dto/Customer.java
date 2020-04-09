package cn.idea360.oracle.dto;

public class Customer {

    private String id;
    private String name;
    private String dept;
    private Integer departmentId;

    public Customer(String id, String name, String dept, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.departmentId = departmentId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
