package cn.idea360.mongo.service;


import cn.idea360.mongo.flow.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class FlowServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void saveUser() {
        //---------------------------开始节点--------------------------------

        // 节点属性
        NodeProperties startNodeProperties = new NodeProperties();

        // 节点模块类型
        Stencil startStencil = new Stencil();
        startStencil.setId(StencilEnum.START.getText());

        // 出口节点
        Outgoing startOutgoing = new Outgoing();
        startOutgoing.setResourceId("line-start");

        // 边界
        UpperLeft startUpperLeft = new UpperLeft();
        startUpperLeft.setX(0);
        startUpperLeft.setY(0);

        LowerRight startLowerRight = new LowerRight();
        startLowerRight.setX(100);
        startLowerRight.setY(80);

        Bounds startBounds = new Bounds();
        startBounds.setUpperLeft(startUpperLeft);
        startBounds.setLowerRight(startLowerRight);


        // 开始节点
        Node startNode = new Node();
        startNode.setResourceId("node-start");
        startNode.setProperties(startNodeProperties);
        startNode.setStencil(startStencil);
        startNode.setOutgoing(Arrays.asList(startOutgoing));
        startNode.setBounds(startBounds);


        //---------------------------普通节点--------------------------------

        // 机器人答复
        AnswerProperties normalAnswerProperties = new AnswerProperties();
        normalAnswerProperties.setModelName("普通话术");
        normalAnswerProperties.setPlayOrder(PlayOrderEnum.RANDOM.getText());
        normalAnswerProperties.setRecordings(Arrays.asList());

        // 基础设置
        BasicProperties normalBasicProperties = new BasicProperties();
        normalBasicProperties.setResourceId(UUID.randomUUID().toString());
        normalBasicProperties.setAgainSence(true);
        normalBasicProperties.setRepeatSence(true);
        normalBasicProperties.setExecuteCount(0);
        normalBasicProperties.setExpression("${executeCount}>3");
        normalBasicProperties.setEnableBreak(true);

        // 意向设置
        IntentionProperties normalIntentionProperties = new IntentionProperties();
        normalIntentionProperties.setIntention(new Intention());


        // 节点属性
        NodeProperties normalNodeProperties = new NodeProperties();
        normalNodeProperties.setAnswerProperties(normalAnswerProperties);
        normalNodeProperties.setBasicProperties(normalBasicProperties);
        normalNodeProperties.setIntentionProperties(normalIntentionProperties);


        // 节点模块类型
        Stencil normalStencil = new Stencil();
        normalStencil.setId(StencilEnum.NORMAL.getText());

        // 肯定
        Answer okAnswer = new Answer();
        okAnswer.setText("肯定");
        okAnswer.setKeywords(new String[]{"ok", "可以"});

        Answer denyAnswer = new Answer();
        denyAnswer.setText("否定");
        denyAnswer.setKeywords(new String[]{"不行", "拒绝"});

        // 出口节点
        // TODO 下一个节点
        Outgoing okOutgoing = new Outgoing();
        okOutgoing.setResourceId("line-end");
        okOutgoing.setAnswers(okAnswer);

        // TODO 挂机节点
        Outgoing denyOutgoing = new Outgoing();
        denyOutgoing.setResourceId("line-end");
        denyOutgoing.setAnswers(denyAnswer);

        // 边界
        UpperLeft normalUpperLeft = new UpperLeft();
        normalUpperLeft.setX(0);
        normalUpperLeft.setY(0);

        LowerRight normalLowerRight = new LowerRight();
        normalLowerRight.setX(100);
        normalLowerRight.setY(80);

        Bounds normalBounds = new Bounds();
        normalBounds.setUpperLeft(normalUpperLeft);
        normalBounds.setLowerRight(normalLowerRight);


        // 普通节点
        Node normalNode = new Node();

        // TODO 节点id
        normalNode.setResourceId("node-normal");
        normalNode.setProperties(normalNodeProperties);
        normalNode.setStencil(normalStencil);
        normalNode.setOutgoing(Arrays.asList(okOutgoing, denyOutgoing));
        normalNode.setBounds(normalBounds);


        //---------------------------挂机节点--------------------------------

        // 节点属性
        NodeProperties hangupNodeProperties = new NodeProperties();

        // 节点模块类型
        Stencil hangupStencil = new Stencil();
        hangupStencil.setId(StencilEnum.HANGUP.getText());

        // 边界
        UpperLeft hangupUpperLeft = new UpperLeft();
        hangupUpperLeft.setX(0);
        hangupUpperLeft.setY(0);

        LowerRight hangupLowerRight = new LowerRight();
        hangupLowerRight.setX(100);
        hangupLowerRight.setY(80);

        Bounds hangupBounds = new Bounds();
        hangupBounds.setUpperLeft(hangupUpperLeft);
        hangupBounds.setLowerRight(hangupLowerRight);

        // 挂机节点
        Node hangupNode = new Node();
        hangupNode.setResourceId("node-hangup");
        hangupNode.setProperties(hangupNodeProperties);
        hangupNode.setStencil(hangupStencil);
        hangupNode.setOutgoing(Arrays.asList());
        hangupNode.setBounds(hangupBounds);

        //---------------------------连线节点1--------------------------------

        // 节点属性
        NodeProperties line1NodeProperties = new NodeProperties();

        // 节点模块类型
        Stencil line1Stencil = new Stencil();
        line1Stencil.setId(StencilEnum.SEQUENCEFLOW.getText());

        // 出口节点
        Outgoing line1Outgoing = new Outgoing();
        line1Outgoing.setResourceId("node-normal");

        // 边界
        UpperLeft line1UpperLeft = new UpperLeft();
        line1UpperLeft.setX(0);
        line1UpperLeft.setY(0);

        LowerRight line1LowerRight = new LowerRight();
        line1LowerRight.setX(100);
        line1LowerRight.setY(80);

        Bounds line1Bounds = new Bounds();
        line1Bounds.setUpperLeft(line1UpperLeft);
        line1Bounds.setLowerRight(line1LowerRight);

        // 连线
        Docker docker1 = new Docker();
        docker1.setX(0);
        docker1.setY(0);

        Docker docker2 = new Docker();
        docker2.setX(80);
        docker2.setY(80);


        // 连线节点
        Node line1Node = new Node();
        line1Node.setResourceId("line-start");
        line1Node.setProperties(line1NodeProperties);
        line1Node.setStencil(line1Stencil);
        line1Node.setOutgoing(Arrays.asList(line1Outgoing));
        line1Node.setBounds(line1Bounds);
        line1Node.setDockers(Arrays.asList(docker1, docker2));


        //---------------------------连线节点2--------------------------------

        // 节点属性
        NodeProperties line2NodeProperties = new NodeProperties();

        // 节点模块类型
        Stencil line2Stencil = new Stencil();
        line2Stencil.setId(StencilEnum.SEQUENCEFLOW.getText());

        // 出口节点
        Outgoing line2Outgoing = new Outgoing();
        line2Outgoing.setResourceId("node-hangup");

        // 边界
        UpperLeft line2UpperLeft = new UpperLeft();
        line2UpperLeft.setX(0);
        line2UpperLeft.setY(0);

        LowerRight line2LowerRight = new LowerRight();
        line2LowerRight.setX(100);
        line2LowerRight.setY(80);

        Bounds line2Bounds = new Bounds();
        line2Bounds.setUpperLeft(line2UpperLeft);
        line2Bounds.setLowerRight(line2LowerRight);

        // 连线
        Docker docker3 = new Docker();
        docker1.setX(0);
        docker1.setY(0);

        Docker docker4 = new Docker();
        docker2.setX(80);
        docker2.setY(80);


        // 连线节点
        Node line2Node = new Node();
        line2Node.setResourceId("line-end");
        line2Node.setProperties(line2NodeProperties);
        line2Node.setStencil(line2Stencil);
        line2Node.setOutgoing(Arrays.asList(line2Outgoing));
        line2Node.setBounds(line2Bounds);
        line2Node.setDockers(Arrays.asList(docker3, docker4));

        //---------------------------完整流程--------------------------------
        // 完整流程
        Flow flow = new Flow();
        flow.setId(UUID.randomUUID().toString());
        flow.setResourceId(UUID.randomUUID().toString());

        // 流程边框
        Bounds flowBounds = new Bounds();
        flow.setBounds(flowBounds);

        // 流程节点
        flow.setChildShapes(Arrays.asList(startNode, normalNode, hangupNode, line1Node, line2Node));

        Flow save = mongoTemplate.save(flow);
        System.out.println(save);
    }

    @Test
    void getFlowById() {
        Query query=new Query(Criteria.where("id").is("c643f430-81c5-402d-a181-68323b7761ae"));
        Flow flow =  mongoTemplate.findOne(query , Flow.class);
        System.out.println(flow);
    }

    @Test
    void delete() {
        Query query=new Query(Criteria.where("id").is("90c09b8e-bcfa-41ee-99c7-06954af1fa20"));
        mongoTemplate.remove(query, Flow.class);
    }

    /**
     * start-line1-node1(肯定)-line2-hangup
     * start-line1-node1(否定)-line2-hangup
     * 
     * 第一次获取资源id: line-start
     * 第二次获取资源id: node-normal
     * Outgoing(resourceId=line-end, answers=Answer(text=肯定, keywords=[ok, 可以]))
     * 第三次获取资源id: line-end
     * 第四次获取资源id: node-hangup
     * 挂机...
     */
    @Test
    void executor() {
        // 当前执行任务节点
        Node currentNode = null;

        // 加载流程
        Query query = new Query(Criteria.where("id").is("c643f430-81c5-402d-a181-68323b7761ae"));
        Flow flow =  mongoTemplate.findOne(query , Flow.class);
        List<Node> childShapes = flow.getChildShapes();

        // 将所有节点载入内存
        Map<String, Node> nodeMap = childShapes.stream().collect(Collectors.toMap(Node::getResourceId, node -> node));

        // 查找开始节点
        currentNode = childShapes.stream().filter(node -> node.getStencil().getId().equals(StencilEnum.START.getText())).findAny().orElse(null);
        String resourceId = currentNode.getOutgoing().get(0).getResourceId();
        System.out.println("第一次获取资源id: " + resourceId);

        // 判断节点是连线节点则继续往下找
        currentNode = nodeMap.get(resourceId);
        if (currentNode.getStencil().getId().equals(StencilEnum.SEQUENCEFLOW.getText())) {
            resourceId = currentNode.getOutgoing().get(0).getResourceId();
            System.out.println("第二次获取资源id: " + resourceId);
        }

        // 任务节点
        currentNode = nodeMap.get(resourceId);
        // 获取录音文件
        List<Recording> recordings = currentNode.getProperties().getAnswerProperties().getRecordings();
        // 返回录音文件给esl

        // 任务执行，等待esl返回语音文字
        String eslText = "ok";

        // 找到匹配的答复
        List<Outgoing> outgoing = currentNode.getOutgoing();
        Outgoing outgoing2 = outgoing.stream().filter(outgoing1 -> Arrays.asList(outgoing1.getAnswers().getKeywords()).contains(eslText)).findFirst().orElse(null);
        System.out.println(outgoing2);
        resourceId = outgoing2.getResourceId();
        System.out.println("第三次获取资源id: " + resourceId);

        // 判断节点是连线节点则继续往下找
        currentNode = nodeMap.get(resourceId);
        if (currentNode.getStencil().getId().equals(StencilEnum.SEQUENCEFLOW.getText())) {
            resourceId = currentNode.getOutgoing().get(0).getResourceId();
            System.out.println("第四次获取资源id: " + resourceId);
        }

        // 获取业务节点
        currentNode = nodeMap.get(resourceId);
        if (currentNode.getStencil().getId().equals(StencilEnum.HANGUP.getText())) {
            System.out.println("挂机...");
        }

    }
}