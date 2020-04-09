package cn.idea360.oracle.vo;

import cn.idea360.oracle.dto.AiProjectGroupUserVo;
import cn.idea360.oracle.model.AiProjectUser;

import java.util.List;

public class AiProjectUserRespVO {

    List<AiProjectGroupUserVo> customers;
    List<AiProjectUser> selfList;

    public List<AiProjectGroupUserVo> getCustomers() {
        return customers;
    }

    public void setCustomers(List<AiProjectGroupUserVo> customers) {
        this.customers = customers;
    }

    public List<AiProjectUser> getSelfList() {
        return selfList;
    }

    public void setSelfList(List<AiProjectUser> selfList) {
        this.selfList = selfList;
    }
}
