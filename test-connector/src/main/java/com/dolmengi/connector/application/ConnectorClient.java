package com.dolmengi.connector.application;

import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.connector.application.service.UserService;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ConnectorClient {

    private final UserService userService;
    private final WebSocketClient webSocketClient;

    public void run() {
        Thread.ofVirtual().start(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\naccountId: ");
            String accountId = scanner.nextLine().trim();

            System.out.print("password: ");
            String password = scanner.nextLine().trim();

            System.out.print("\n");
            log.info("accountId: {}, password: {}", accountId, password);

            String token = userService.getToken(accountId, password);
            log.info("token: {}", token);

            SubscriptionInfo subscriptionInfo = userService.getSubscriptionInfo(token);
            log.info("subscriptionInfo: {}", subscriptionInfo);

            webSocketClient.connect(subscriptionInfo);
        });
    }

}
