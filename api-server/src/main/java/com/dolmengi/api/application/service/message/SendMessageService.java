package com.dolmengi.api.application.service.message;

import com.dolmengi.api.application.port.in.message.SendMessageUseCase;
import com.dolmengi.common.domain.message.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SendMessageService implements SendMessageUseCase {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void sendMessage() {
        log.info("메시지 저장");

        eventPublisher.publishEvent(new MessageEvent("메시지 발송"));
    }

}
