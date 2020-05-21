package cn.idea360.mp.model;

import cn.idea360.mp.converter.XStreamCDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@XStreamAlias("xml")
public class XmlMessage implements Serializable {

    @XStreamAlias("ToUserName")
    @XStreamConverter(XStreamCDataConverter.class)
    private String toUser;
    @XStreamAlias("FromUserName")
    @XStreamConverter(XStreamCDataConverter.class)
    private String fromUser;
    @XStreamAlias("CreateTime")
    private Long createTime;
    @XStreamAlias("MsgType")
    @XStreamConverter(XStreamCDataConverter.class)
    private String msgType;
    @XStreamAlias("Event")
    @XStreamConverter(XStreamCDataConverter.class)
    private String event;
    @XStreamAlias("EventKey")
    @XStreamConverter(XStreamCDataConverter.class)
    private String eventKey;
    @XStreamAlias("Ticket")
    @XStreamConverter(XStreamCDataConverter.class)
    private String ticket;
    @XStreamAlias("MsgID")
    private Long msgId;
    @XStreamAlias("Status")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String status;
}
