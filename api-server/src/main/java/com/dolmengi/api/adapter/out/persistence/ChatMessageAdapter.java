package com.dolmengi.api.adapter.out.persistence;

import com.dolmengi.api.adapter.out.persistence.repository.ChatMessageRepository;
import com.dolmengi.api.application.port.out.persistence.ChatMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatMessageAdapter implements ChatMessagePort {

    private final ChatMessageRepository repository;



}
