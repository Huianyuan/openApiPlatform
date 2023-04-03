package com.example.imitateinterface;

import com.example.openapiclientsdk.client.Zclient;
import com.example.openapiclientsdk.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ImitateInterfaceApplicationTests {

    @Resource
    private Zclient zclient;

    /**
     * 测试SDK引入是否成功
     * 注：这里应该创建新的项目，引入SDK，向模拟接口项目发起请求。这里就在模拟接口项目下简单测试
     */
    @Test
    void contextLoads() {
        String result1=zclient.getNameByGet("get test");
        String result2=zclient.getNameByPost("post test");
        User user = new User();
        user.setUserName("object test");
        String result3=zclient.getUserNameByPost(user);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

    }

}
