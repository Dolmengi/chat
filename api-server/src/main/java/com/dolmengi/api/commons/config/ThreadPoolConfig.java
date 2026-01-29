package com.dolmengi.api.commons.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Bean
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);        // 기본 유지 스레드
        executor.setMaxPoolSize(16);         // 최대 확장 스레드
        executor.setQueueCapacity(100);      // 대기 큐 크기
        executor.setThreadNamePrefix("default-");

        // 큐가 가득 찼을 때의 처리 전략 (예: 호출한 스레드에서 직접 실행)
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // Bean 생명주기에 맞게 초기화
        executor.initialize();

        return executor;
    }

}
