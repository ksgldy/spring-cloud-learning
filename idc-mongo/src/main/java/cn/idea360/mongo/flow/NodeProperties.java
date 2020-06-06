package cn.idea360.mongo.flow;

import lombok.Data;

/**
 * 子节点属性
 */
@Data
public class NodeProperties {

    /**
     * 机器人答复(挂机模块只有机器人答复)
     */
    private AnswerProperties answerProperties;
    /**
     * 基础设置
     */
    private BasicProperties basicProperties;
    /**
     * 意图设置
     */
    private IntentionProperties intentionProperties;
}
