package cn.idea360.mongo.flow;

import lombok.Data;

/**
 * 出口节点属性
 */
@Data
public class Outgoing {

    /**
     * 出口节点唯一id(一般是连线id)
     */
    private String resourceId;

    /**
     * 回答节点
     */
    private Answer answers;
}
