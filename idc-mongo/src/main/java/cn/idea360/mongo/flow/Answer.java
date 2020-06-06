package cn.idea360.mongo.flow;

import lombok.Data;

/**
 * 子节点模块中的回答节点
 */
@Data
public class Answer {

    /**
     * 展示标准答案
     */
    private String text;

    /**
     * 匹配关键词
     */
    private String[] keywords;

}
