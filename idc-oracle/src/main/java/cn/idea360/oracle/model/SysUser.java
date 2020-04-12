package cn.idea360.oracle.model;

import java.util.Date;

public class SysUser {

    // 主键
    private Integer id;
    // 用户名(唯一)
    private String userName;
    // 工号(递增)
    private Integer jobNumber;
    // 创建时间
    private Date createTime;

    public SysUser(String userName, Integer jobNumber) {
        this.userName = userName;
        this.jobNumber = jobNumber;
        this.createTime = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
