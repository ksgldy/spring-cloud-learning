package cn.idea360.mongo.flow;

import lombok.Data;

/**
 * 基础设置
 */
@Data
public class BasicProperties {

    /**
     * 客户要求重复
     */
    private boolean repeatSence;


    /**
     * 再次进入节点时
     */
    private boolean againSence;

    /**
     * 当前节点执行次数
     */
    private int executeCount;

    /**
     * 条件表达式(客户连续n次要求机器人重复   ${executeCount}>3)
     */
    private String expression;

    /**
     * 满足条件表达式跳转节点
     */
    private String resourceId;

    /**
     * 是否允许打断
     */
    private boolean enableBreak;


}
