package com.dolmengi.connector.adapter.out;

import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.connector.adapter.out.dto.LoginRequest;
import feign.Logger.Level;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "api-server", url = "${urls.api-server}", configuration = ApiClient.ApiFeignConfig.class, fallbackFactory = ApiClientFallbackFactory.class)
public interface ApiClient {

    @PostMapping(value = "/api/login")
    ResponseEntity<Void> login(@RequestBody LoginRequest request);

    @GetMapping(value = "/api/subscriptionInfo")
    SubscriptionInfo subscriptionInfo(@RequestHeader("Authorization") String accessToken);

    class ApiFeignConfig implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate requestTemplate) {
            requestTemplate.header("Content-Type", "application/json");
        }

        @Bean
        Level logLevel() {
            return Level.FULL;
        }

    }

}
