/*
 * Copyright © 2020-2025 organization opcooc
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package com.example.demo.config;

import com.opcooc.storage.spring.boot.autoconfigure.ClientDriverProperty;
import com.opcooc.storage.support.DecryptCallback;
import com.opcooc.storage.toolkit.CryptoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author shenqicheng
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
