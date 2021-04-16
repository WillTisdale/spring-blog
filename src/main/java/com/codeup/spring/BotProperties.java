package com.codeup.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bot")
public class BotProperties {
    private String token = "ODMxOTQzNjYzOTg2NTQwNTg1.YHcl6g.NTmnBl5AGm_jnn0NIc8HKmZKcoM";

    public String getToken(){
        return token;
    }
}
