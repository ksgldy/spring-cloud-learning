package cn.idea360.mongo.data.flow;

import java.util.List;

/**
 * 子节点
 */
public class Node {

    /**
     * 子节点id
     */
    private String resourceId;
    /**
     * 子节点基本属性
     */
    private NodeProperties properties;
    /**
     * 子节点模块类型
     */
    private Stencil stencil;

    /**
     * 出口节点
     */
    private List<Outgoing> outgoing;
    /**
     * 节点边界
     */
    private Bounds bounds;

    /**
     * 连线节点
     */
    private List<Docker> dockers;

    /**
     * 目标节点(连线节点独有)
     */
    private Outgoing target;

}
