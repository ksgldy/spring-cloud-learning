package cn.idea360.mongo.flow;

import lombok.Data;

/**
 * 录音
 */
@Data
public class Recording {


    private String id;

    /**
     * 路由地址
     */
    private String recordingUrl;
}
