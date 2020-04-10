package cn.idea360.oracle.vo;

import java.util.List;

public class AiProjectGroupDetailRespVO {

    private String groupName;
    List<AiProjectGroupUserVO> currCustomers;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<AiProjectGroupUserVO> getCurrCustomers() {
        return currCustomers;
    }

    public void setCurrCustomers(List<AiProjectGroupUserVO> currCustomers) {
        this.currCustomers = currCustomers;
    }
}
