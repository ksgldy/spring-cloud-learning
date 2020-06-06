package cn.idea360.mongo.flow;

import lombok.Data;

import java.util.List;

/**
 * 子节点
 */
@Data
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
     * 子节点模块类型(普通模块、任答模块、挂机模块、转人工模块)
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
