package com.dolmengi.api.adapter.out.persistence;

import com.dolmengi.api.adapter.out.persistence.repository.ChatChannelUserRepository;
import com.dolmengi.api.application.port.out.persistence.ChatChannelUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatChannelUserAdapter implements ChatChannelUserPort {

    private final ChatChannelUserRepository repository;



}
