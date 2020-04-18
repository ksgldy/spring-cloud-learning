package cn.idea360.idcwechat.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

@XStreamAlias("xml")
public class Tm  implements Serializable {

    @XStreamAlias("ToUserName")
    @XStreamConverter(XStreamCDataConverter.class)
    private String toUserName;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}
