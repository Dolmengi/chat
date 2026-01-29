package com.dolmengi.api.adapter.in.rest.user;

import com.dolmengi.api.application.port.in.user.SubscriptionInfoUseCase;
import com.dolmengi.common.domain.account.SubscriptionInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscriptionInfoController {

    private final SubscriptionInfoUseCase useCase;

    @GetMapping("/api/subscriptionInfo")
    public ResponseEntity<SubscriptionInfo> subscriptionInfo() {
        SubscriptionInfo subscriptionInfo = useCase.subscriptionInfo();

        return ResponseEntity.ok().body(subscriptionInfo);
    }

}
