package com.example.openapiclientsdk;

import com.example.openapiclientsdk.client.Zclient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author :lhy
 * @description :TODO
 * @date :2023/04/03 下午 02:20
 */


@Configuration
@ConfigurationProperties("openapi.clent")
@ComponentScan
@Data
public class ZClientConfig {

    private String accessKey;
    private String secretKey;


    @Bean
    public Zclient openApi(){
        return new Zclient(accessKey, secretKey);
    }


}
