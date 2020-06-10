package cn.idea360.mongo.flow;

import java.io.Serializable;

/**
 * 节点类型
 *
 *
 * 只有普通节点可以编辑路由
 * 任答节点出口唯一
 *
 */
public enum  StencilEnum implements Serializable {

    START("start", "开始节点"),
    NORMAL("normal", "普通模块"),
    ANYANSWER("anyanswer", "任答模块"),
    HANGUP("hangup", "挂机模块"),
    ARTIFICIAL("artificial", "转人工模块"),
    SEQUENCEFLOW("sequenceflow", "连线节点"),
    ;

    private String value;
    private String text;

    StencilEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
