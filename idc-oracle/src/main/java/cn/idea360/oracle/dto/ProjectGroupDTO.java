package cn.idea360.oracle.dto;

import cn.idea360.oracle.model.AiProjectUser;

import java.util.List;

public class ProjectGroupDTO {

    private Long id;
    private String groupName;
    private List<AiProjectUser> customers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<AiProjectUser> getCustomers() {
        return customers;
    }

    public void setCustomers(List<AiProjectUser> customers) {
        this.customers = customers;
    }
}
