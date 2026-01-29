package com.dolmengi.connector.adapter.out;

import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.connector.adapter.out.dto.LoginRequest;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiClientFallbackFactory implements FallbackFactory<ApiClient> {

    @Override
    public ApiClient create(Throwable cause) {
        return new ApiClient() {

            @Override
            public ResponseEntity<Void> login(LoginRequest request) {
                return null;
            }

            @Override
            public SubscriptionInfo subscriptionInfo(String accessToken) {
                return null;
            }

        };
    }

}
