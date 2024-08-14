package com.malikov.springbootlesson9task1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("test")
    public YamlProperties testYamlProperties() {
        return new YamlProperties("test-YAML", "testing", false, Arrays.asList("www.abc.test.com", "www.xyz.test.com"));
    }

    @Bean
    @Profile("prod")
    public YamlProperties prodYamlProperties() {
        return new YamlProperties("prod-YAML", "production", true, Arrays.asList("www.abc.com", "www.xyz.com"));
    }

}
