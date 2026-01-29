package com.dolmengi.connector.application;

import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.connector.application.handler.WebSocketStompSessionHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Component
public class WebSocketClient {

    public void connect(SubscriptionInfo subscriptionInfo) {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

        WebSocketStompSessionHandler sessionHandler = new WebSocketStompSessionHandler(subscriptionInfo.topic());

        stompClient.connectAsync(subscriptionInfo.url(), headers, sessionHandler);
    }

}
