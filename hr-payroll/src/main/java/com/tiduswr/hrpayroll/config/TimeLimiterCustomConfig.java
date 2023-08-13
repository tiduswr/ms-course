package com.tiduswr.hrpayroll.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;

@Configuration
public class TimeLimiterCustomConfig {
    
    @Bean
    TimeLimiterRegistry timeLimiterConfig(){
        TimeLimiterConfig timerConfig =  TimeLimiterConfig.custom()
            .cancelRunningFuture(true)
            .timeoutDuration(Duration.ofMillis(30000))
            .build();
        return TimeLimiterRegistry.of(timerConfig);
    }

}
