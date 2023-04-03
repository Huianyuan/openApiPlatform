package com.example.openapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.openapiclientsdk.entity.User;

import java.util.HashMap;
import java.util.Map;

import static com.example.openapiclientsdk.utils.SignUtils.getSign;


/**
 * @author :lhy
 * @description :调用第三方接口的客户端
 * @date :2023/03/30 下午 03:49
 */

public class Zclient {

    private  String accessKey;
    private String secretKey;

    public Zclient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String,Object> paramMap=new HashMap<>();
        paramMap.put("name",name);
        String result= HttpUtil.get("http://localhost:8123/api/name",paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost( String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String,Object> paramMap=new HashMap<>();
        paramMap.put("name",name);
        String result= HttpUtil.post("http://localhost:8123/api/name",paramMap);
        System.out.println(result);
        return result;
    }

    private Map<String, String> getHeaders(String body) {
        Map<String,String> headers=new HashMap<>();
        headers.put("accessKey",accessKey);
        // headers.put("secretKey",secretKey);
        headers.put("nonce", RandomUtil.randomNumbers(4));
        headers.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        headers.put("body",body);
        headers.put("sign",getSign(body,secretKey));

        return headers;
    }



    public String getUserNameByPost(User user) {
        String json= JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/name/user")
                .addHeaders(getHeaders(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result= httpResponse.body();
        System.out.println(result);
        return result;
    }
}
