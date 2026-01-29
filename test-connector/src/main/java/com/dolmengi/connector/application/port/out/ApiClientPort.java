package com.dolmengi.connector.application.port.out;

import com.dolmengi.common.domain.account.SubscriptionInfo;

public interface ApiClientPort {

    String login(String accountId, String password);

    SubscriptionInfo subscription(String token);

    void sendMessage(String message);

}
