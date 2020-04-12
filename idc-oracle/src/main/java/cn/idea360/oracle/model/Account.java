package cn.idea360.oracle.model;

import lombok.Data;

import java.util.Date;

public class Account {

    // 主键
    private Integer id;
    // 用户名(唯一)
    private String realName;
    // 工号(递增)
    private Integer jobNumber;
    // 创建时间
    private Date createTime;

    public Account() {
    }

    public Account(String realName, Integer jobNumber) {
        this.realName = realName;
        this.jobNumber = jobNumber;
        this.createTime = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", jobNumber=" + jobNumber +
                ", createTime=" + createTime +
                '}';
    }
}
