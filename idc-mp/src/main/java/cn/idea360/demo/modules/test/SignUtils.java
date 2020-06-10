package cn.idea360.demo.modules.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * 参数验签
 *
 * 现没有统一的签名机制，无需按业务区分。采用唯一的appKey和appSecret
 */
public class SignUtils {

    private static final Logger logger = LoggerFactory.getLogger(SignUtils.class);

    public static final String appKey = "wx3f2f5354f615c637";
    public static final String appSecret = "80ae2299328c6c8f6ae0c774a69b08b6";

    /**
     * 获取签名参数
     * @param params
     * @return
     */
    public static String getSignData(Map<String, String> params) {
        StringBuilder content = new StringBuilder();
        // key 自然排序
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if ("sign".equals(key)) {
                continue;
            }
            String value = params.get(key);
            if (value != null) {
                content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
            } else {
                content.append(i == 0 ? "" : "&").append(key).append("=");
            }
        }

        return content.toString();
    }

    /**
     * 参数签名
     * @param params
     */
    public static String sign(Map<String, String> params) {

        String outSignData = getSignData(params);
        logger.info("outSignData:{}", outSignData);

        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, appSecret).hmac(outSignData);
        String sign = new String(Base64.encodeBase64(hmac));
        logger.info("sign:{}", sign);
        return sign;
    }

    /**
     * 参数验签
     * @param params
     */
    public static boolean checkSignature(Map<String, String> params) {
        // 校验请求是否过期
        String inTimeStamp = params.getOrDefault("_t", "0");
        LocalDateTime inTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(inTimeStamp)), ZoneOffset.ofHours(8));
        Duration duration = Duration.between(inTime, LocalDateTime.now());
        long seconds = duration.get(SECONDS);
        System.out.println("seconds: " + seconds);
        if (seconds > 3 * 60) {
            System.out.println("请求超时");
            return Boolean.FALSE;
        }

        String inSignData = getSignData(params);
        logger.info("inSignData:{}", inSignData);

        byte[] inHmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, appSecret).hmac(inSignData);
        String sign2 = new String(Base64.encodeBase64(inHmac));
        logger.info("sign2:{}", sign2);

        String sign1 = params.getOrDefault("sign", "");
        return sign1.equals(sign2);
    }

    /**
     * 2020-06-08 15:36:17 [cn.easyliao.mp.utils.SignUtils]-[INFO] outSignData:_t=1591601777975&appKey=wx3f2f5354f615c637&c=v1&sessionId=123&state=&u=v2
     * 2020-06-08 15:36:18 [cn.easyliao.mp.utils.SignUtils]-[INFO] sign:7AeJShLz45QXq94JKG8xV3dQL6o=
     * seconds: 0
     * 2020-06-08 15:36:18 [cn.easyliao.mp.utils.SignUtils]-[INFO] inSignData:_t=1591601777975&appKey=wx3f2f5354f615c637&c=v1&sessionId=123&state=&u=v2
     * 2020-06-08 15:36:18 [cn.easyliao.mp.utils.SignUtils]-[INFO] sign2:7AeJShLz45QXq94JKG8xV3dQL6o=
     * true
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        // TODO 传入为各个服务分配的的appKey
        params.put("appKey", appKey);

        // 业务参数
        params.put("c", "v1");
        params.put("u", "v2");
        params.put("_t", String.valueOf(System.currentTimeMillis()));
        params.put("state", null);
        params.put("sessionId", "123");


        // 签名
        String sign1 = sign(params);
        params.put("sign", sign1);

        // 验签
        boolean checkSignature = checkSignature(params);
        System.out.println(checkSignature);

    }

}
