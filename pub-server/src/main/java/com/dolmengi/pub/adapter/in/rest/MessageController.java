package com.dolmengi.pub.adapter.in.rest;

import com.dolmengi.common.domain.message.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

    @SendTo("/topic/messages")
    @MessageMapping("/send")
    public MessageEvent send(MessageEvent message) {
        log.info("Received: {}", message);

        return message;
    }

}
