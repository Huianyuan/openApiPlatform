package com.example.openapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author :lhy
 * @description :签名认证
 * @date :2023/03/30 下午 08:41
 */
public class SignUtils {
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content=body+'.'+secretKey;
        return md5.digestHex(content);
    }
}
