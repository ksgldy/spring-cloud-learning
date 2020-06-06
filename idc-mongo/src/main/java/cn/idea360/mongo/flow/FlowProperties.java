package cn.idea360.mongo.flow;

import lombok.Data;

/**
 * 流程基本属性
 */
@Data
public class FlowProperties {


    /**
     * 机器人id
     */
    private Long robotId;
    /**
     * 机器人名称
     */
    private String robotName;

    /**
     * 备注
     */
    private String documentation;

    /**
     * 流程版本
     */
    private String processVersion;

}
