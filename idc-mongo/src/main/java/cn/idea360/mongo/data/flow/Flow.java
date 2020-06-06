package cn.idea360.mongo.data.flow;

import org.springframework.data.annotation.Id;

import java.util.List;


/**
 * 流程
 */
public class Flow {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 流程唯一id
     */
    private String resourceId;


    /**
     * 流程基本属性
     */
    private FlowProperties flowProperties;

    /**
     * 流程边界
     */
    private Bounds bounds;

    /**
     * 流程子节点
     */
    private List<Node> childShapes;

}
