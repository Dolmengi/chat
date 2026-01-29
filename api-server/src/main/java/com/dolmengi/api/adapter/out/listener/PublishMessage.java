package com.dolmengi.api.adapter.out.listener;

import com.dolmengi.common.domain.message.MessageEvent;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PublishMessage implements Consumer<Message<MessageEvent>> {

    @Override
    public void accept(Message<MessageEvent> message) {
        //log.info("key: {}", messageEvent.getHeaders().get(KafkaHeaders.RECEIVED_KEY));
        log.info("key: {}", message.getHeaders());
        MessageEvent clusterResponse = message.getPayload();
        log.info("payload: {}", clusterResponse);
    }

}
