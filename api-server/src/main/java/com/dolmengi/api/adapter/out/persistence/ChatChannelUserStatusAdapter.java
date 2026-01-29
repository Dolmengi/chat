package com.dolmengi.api.adapter.out.persistence;

import com.dolmengi.api.adapter.out.persistence.repository.ChatChannelUserStatusRepository;
import com.dolmengi.api.application.port.out.persistence.ChatChannelUserStatusPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatChannelUserStatusAdapter implements ChatChannelUserStatusPort {

    private final ChatChannelUserStatusRepository repository;



}
