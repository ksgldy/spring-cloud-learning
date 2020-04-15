package cn.idea360.idc.service;

import cn.idea360.idc.config.Config;
import cn.idea360.idc.utils.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxService {

    private static final Logger logger = LoggerFactory.getLogger(WxService.class);

    @Autowired
    private Config.WxConfig wxConfig;

    public boolean checkSignature(String timestamp, String nonce, String signature) {
        try {
            logger.info("token={}", wxConfig.getToken());
            return SHA1.gen(new String[]{wxConfig.getToken(), timestamp, nonce}).equals(signature);
        } catch (Exception var5) {
            logger.error("Checking signature failed, and the reason is :" + var5.getMessage());
            return false;
        }
    }
}
