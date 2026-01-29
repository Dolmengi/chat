package com.dolmengi.connector.adapter.out;

import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.connector.adapter.out.dto.LoginRequest;
import com.dolmengi.connector.application.port.out.ApiClientPort;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class APiClientAdapter implements ApiClientPort {

    private final ApiClient client;

    @Override
    public String login(String accountId, String password) {
        ResponseEntity<Void> response = client.login(new LoginRequest(accountId, password));
        response.getStatusCode();
        if (!Objects.equals(HttpStatusCode.valueOf(200), response.getStatusCode())) {
            throw new RuntimeException();
        }

        HttpHeaders headers = response.getHeaders();
        String authorization = headers.getFirst("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        } else {
            return null;
        }
    }

    @Override
    public SubscriptionInfo subscription(String token) {
        return client.subscriptionInfo("Bearer " + token);
    }

    @Override
    public void sendMessage(String message) {

    }

}
