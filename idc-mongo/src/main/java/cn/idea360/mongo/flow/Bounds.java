package cn.idea360.mongo.flow;

import lombok.Data;

/**
 * 方框
 */
@Data
public class Bounds {
    /**
     * 左上角坐标
     */
    private UpperLeft upperLeft;
    /**
     * 右下角坐标
     */
    private LowerRight lowerRight;

}
