package cn.idea360.oracle.vo;

import cn.idea360.oracle.dto.Customer;
import cn.idea360.oracle.model.AiProjectUser;

import java.util.List;

public class AiProjectUserRespVO {

    List<Customer> customers;
    List<AiProjectUser> groupList;
    List<AiProjectUser> selfList;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<AiProjectUser> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<AiProjectUser> groupList) {
        this.groupList = groupList;
    }

    public List<AiProjectUser> getSelfList() {
        return selfList;
    }

    public void setSelfList(List<AiProjectUser> selfList) {
        this.selfList = selfList;
    }
}
