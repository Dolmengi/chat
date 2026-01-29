package com.dolmengi.connector.application.handler;

import com.dolmengi.common.domain.message.MessageEvent;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

@Slf4j
public class WebSocketStompSessionHandler extends StompSessionHandlerAdapter {

    private final String topic;

    public WebSocketStompSessionHandler(String topic) {
        this.topic = topic;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        Scanner scanner = new Scanner(System.in);

        session.subscribe("/topic/messages", new StompFrameHandler() {
        //session.subscribe(topic, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return MessageEvent.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                MessageEvent event = (MessageEvent) payload;
                //log.info("Received: {}", event);

                synchronized (System.out) {
                    System.out.println("<<<<< Received: " + event.message());
                    System.out.print("\n>>>>> Message: ");
                }
            }
        });

        Thread.ofVirtual().start(() -> {
            try (scanner) {
                System.out.print("\n>>>>> Message: ");
                while (true) {
                    String content = scanner.nextLine().trim();

                    if (Objects.equals("exit", content.toLowerCase())) {
                        System.out.println("Shutdown...");
                        session.disconnect();
                        System.exit(0);
                    }

                    session.send("/app/send", new MessageEvent(content));
                }
            } catch (Exception e) {
                log.error("error: {}", e.getMessage());
            }
        });
    }

    @Override
    public void handleTransportError(StompSession session, Throwable throwable) {
        log.error("Transport error: {}", throwable.getMessage());
    }

}
