package cn.idea360.mongo.entity;

import org.springframework.data.annotation.Id;

public class RobotFlow {

    /**
     * 主键
     */
    @Id
    public String id;

    /**
     * 机器人id
     */
    private Long robotId;
    /**
     * 机器人名称
     */
    private String robotName;


}
