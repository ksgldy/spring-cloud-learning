package cn.idea360.oracle.model;

import java.util.Date;

public class AiProjectGroup {

    private Long id;
    private Integer companyId;
    private String groupName;
    private Integer rank;
    private Date createTime;
    private Date updateTime;
    private String creater;
    private String updater;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Override
    public String toString() {
        return "AiProjectGroup{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", groupName='" + groupName + '\'' +
                ", rank=" + rank +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creater='" + creater + '\'' +
                ", updater='" + updater + '\'' +
                '}';
    }
}
