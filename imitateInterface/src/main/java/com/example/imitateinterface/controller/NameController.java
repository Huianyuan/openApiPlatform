package com.example.imitateinterface.controller;

import com.example.openapiclientsdk.entity.User;
import com.example.openapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :lhy
 * @description :TODO
 * @date :2023/03/30 下午 03:11
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name) {
        return "Get 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "Post 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String secretKey = request.getHeader("secretKey");
        String nonce=request.getHeader("nonce");
        String timestamp=request.getHeader("timestamp");
        String sign=request.getHeader("sign");
        String body=request.getHeader("body");
        //todo 实际从数据库中获取
        if (!accessKey.equals("ceshi")) {
            throw new RuntimeException("无权限");
        }

        if(Long.parseLong(nonce)<100){
            throw new RuntimeException("无权限");
        }
        //todo 时间校验，当前时间和调用时间不能超过5分钟
        // if(timestamp)
        //todo 从数据库中获取
        String serverSign = SignUtils.getSign(body, "abcdef");
        if(!sign.equals(serverSign)){
            throw new RuntimeException("无权限");
        }
        return "post 用户的名字是" + user.getUserName();
    }

}
