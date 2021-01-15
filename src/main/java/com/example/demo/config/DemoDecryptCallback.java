package com.example.demo.config;

import com.opcooc.storage.spring.boot.autoconfigure.ClientDriverProperty;
import com.opcooc.storage.support.DecryptCallback;
import com.opcooc.storage.toolkit.CryptoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 自定义信息解密
 */
@Slf4j
public class DemoDecryptCallback implements DecryptCallback {
    @Override
    public String decrypt(ClientDriverProperty config, String cipherText) {
        if (StringUtils.hasText(cipherText)) {
            try {
                String decryptText = CryptoUtils.decrypt(config.getPublicKey(), cipherText);
                // todo 线上环境建议不要进行打印解密文字
                log.info("DemoDecryptCallback.decrypt success!, decryptText:[{}]", decryptText);
                return decryptText;
            } catch (Exception ex) {
                log.error("DemoDecryptCallback.decrypt error，continue......");
            }
        }
        // 报错了不做异常抛出，有可能是本地测试密码不需要解密
        return cipherText;
    }
}
