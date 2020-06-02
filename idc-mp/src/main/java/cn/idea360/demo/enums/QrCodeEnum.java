package cn.idea360.demo.enums;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码状态
 */
@Getter
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QrCodeEnum implements Serializable {

    NOT_SCAN(1, "未被扫描"),
    SCANNED(2, "被扫描"),
    VERIFIED(3, "确认完后"),
    EXPIRED(4, "过期"),
    FINISH(5, "完成"),
    ;

    private final int value;
    private final String text;

    QrCodeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    private static final Map<Integer, QrCodeEnum> map = new HashMap<>();

    static {
        Arrays.stream(QrCodeEnum.values()).forEach(e -> map.put(e.getValue(), e));
    }

    public static QrCodeEnum getEnumByValue(int value) {
        return map.getOrDefault(value, null);
    }
}
