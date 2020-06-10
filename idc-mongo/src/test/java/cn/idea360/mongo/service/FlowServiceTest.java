package cn.idea360.mongo.service;

import cn.idea360.mongo.flow.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlowServiceTest {

    @Autowired
    private FlowService flowService;

    @Test
    void saveUser() {
        //---------------------------开始节点--------------------------------
        // 机器人答复
        AnswerProperties startAnswerProperties = new AnswerProperties();
        startAnswerProperties.setModelName("开始话术");
        startAnswerProperties.setPlayOrder(PlayOrderEnum.RANDOM.getText());
        startAnswerProperties.setRecordings(Arrays.asList());

        // 基础设置
        BasicProperties startBasicProperties = new BasicProperties();
        startBasicProperties.setResourceId(UUID.randomUUID().toString());
        startBasicProperties.setAgainSence(true);
        startBasicProperties.setRepeatSence(true);
        startBasicProperties.setExecuteCount(0);
        startBasicProperties.setExpression("${executeCount}>3");
        startBasicProperties.setResourceId(null);
        startBasicProperties.setEnableBreak(true);

        // 意向设置
        IntentionProperties startIntentionProperties = new IntentionProperties();
        startIntentionProperties.setIntention(new Intention());


        // 节点属性
        NodeProperties startNodeProperties = new NodeProperties();
        startNodeProperties.setAnswerProperties(startAnswerProperties);
        startNodeProperties.setBasicProperties(startBasicProperties);
        startNodeProperties.setIntentionProperties(startIntentionProperties);


        // 节点模块类型
        Stencil startStencil = new Stencil();
        startStencil.setId(StencilEnum.NORMAL.getText());

        // 肯定
        Answer okAnswer = new Answer();
        okAnswer.setText("肯定");
        okAnswer.setKeywords(new String[]{"ok", "可以"});

        Answer denyAnswer = new Answer();
        denyAnswer.setText("否定");
        denyAnswer.setKeywords(new String[]{"不行", "拒绝"});

        // 出口节点
        Outgoing okOutgoing = new Outgoing();
        okOutgoing.setResourceId(UUID.randomUUID().toString());
        okOutgoing.setAnswers(okAnswer);

        Outgoing denyOutgoing = new Outgoing();
        denyOutgoing.setResourceId(UUID.randomUUID().toString());
        denyOutgoing.setAnswers(denyAnswer);

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
        startNode.setResourceId(UUID.randomUUID().toString());
        startNode.setProperties(startNodeProperties);
        startNode.setStencil(startStencil);
        startNode.setOutgoing(Arrays.asList(okOutgoing, denyOutgoing));
        startNode.setBounds(startBounds);




        //---------------------------完整流程--------------------------------
        // 完整流程
        Flow flow = new Flow();
        flow.setId(UUID.randomUUID().toString());
        flow.setResourceId(UUID.randomUUID().toString());

        // 流程边框
        Bounds flowBounds = new Bounds();
        flow.setBounds(flowBounds);

        // 流程节点
        flow.setChildShapes(Arrays.asList(startNode));

        flowService.saveFlow(flow);
    }

    @Test
    void getFlowById() {
        Flow flow = flowService.getFlowById("c5a6c135-2009-4099-b93c-2e303b8d674b");
        System.out.println(flow);
    }
}