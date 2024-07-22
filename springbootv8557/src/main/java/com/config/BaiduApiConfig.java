package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "baidu-api")
@Data
public class BaiduApiConfig {

    private String id;

    private String apiKey;

    private String secretKey;

}
