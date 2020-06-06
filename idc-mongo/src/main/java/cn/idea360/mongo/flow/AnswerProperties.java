package cn.idea360.mongo.flow;

import lombok.Data;

import java.util.List;

/**
 * 机器人答复节点
 */
@Data
public class AnswerProperties {

    /**
     * 模块名
     */
    private String modelName;

    /**
     * 播放顺序(PlayOrderEnum)
     */
    private String playOrder;

    /**
     * 录音文件
     */
    private List<Recording> recordings;

}
