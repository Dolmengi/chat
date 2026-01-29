package com.dolmengi.connector.application.service;

import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.connector.application.port.out.ApiClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ApiClientPort apiClientPort;

    public String getToken(String accountId, String password) {
        return apiClientPort.login(accountId, password);
    }

    public SubscriptionInfo getSubscriptionInfo(String token) {
        return apiClientPort.subscription(token);
    }

}
