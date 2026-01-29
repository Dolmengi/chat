package com.dolmengi.connector.adapter.out;

import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.connector.adapter.out.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface ApiClient {

    @PostExchange(value = "/api/login")
    ResponseEntity<Void> login(@RequestBody LoginRequest request);

    @GetExchange(value = "/api/subscriptionInfo")
    SubscriptionInfo subscriptionInfo(@RequestHeader("Authorization") String accessToken);

}
