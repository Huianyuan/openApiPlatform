package com.example.imitateinterface;

import com.example.imitateinterface.client.Zclient;
import com.example.imitateinterface.entity.User;


/**
 * @author :lhy
 * @description :TODO
 * @date :2023/03/30 下午 04:02
 */
public class Main {
    public static void main(String[] args) {

        String accessed ="ceshi";
        String secretKey="abcdef";
        Zclient zclient = new Zclient(accessed,secretKey);
        // String result1 = zclient.getNameByGet("huhu");
        // String result2 = zclient.getNameByPost("baldf");

        User user = new User();
        user.setUserName("laosna");
        String result3 = zclient.getUserNameByPost(user);

        // System.out.println(result1);
        // System.out.println(result2);
        System.out.println(result3);
    }

}
