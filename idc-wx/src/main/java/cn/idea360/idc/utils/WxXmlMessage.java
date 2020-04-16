package cn.idea360.idc.utils;

import java.io.Serializable;

public class WxXmlMessage implements Serializable {

    private String toUser;
    private String fromUser;
    private Long createTime;
    private String msgType;
    private String event;
    private String eventKey;
    private String ticket;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "WxXmlMessage{" +
                "toUser='" + toUser + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                ", event='" + event + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
