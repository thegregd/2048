package com.greg._2048.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Random;

@Configuration
public class GameConfig implements WebMvcConfigurer {
    
    @Bean("hint")
    public StringBuffer stringBuffer() {
        return new StringBuffer();
    }
    
    @Bean
    public Random rand() {
        return new Random();
    }
}
