package com.dolmengi.pub.adapter.out.publish;

import com.dolmengi.common.domain.message.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessagePublish {

    private final SimpMessagingTemplate template;

    public void publish(MessageEvent message) {
        log.info("publish: {}", message);

        template.convertAndSend("/topic/messages", message);
    }

}
