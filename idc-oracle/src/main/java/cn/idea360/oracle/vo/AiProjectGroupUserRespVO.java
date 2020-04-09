package cn.idea360.oracle.vo;

import java.util.List;

public class AiProjectGroupUserRespVO {

    List<AiProjectGroupUserVO> allCustomers;
    List<AiProjectGroupUserVO> currCustomers;

    public List<AiProjectGroupUserVO> getAllCustomers() {
        return allCustomers;
    }

    public void setAllCustomers(List<AiProjectGroupUserVO> allCustomers) {
        this.allCustomers = allCustomers;
    }

    public List<AiProjectGroupUserVO> getCurrCustomers() {
        return currCustomers;
    }

    public void setCurrCustomers(List<AiProjectGroupUserVO> currCustomers) {
        this.currCustomers = currCustomers;
    }
}
