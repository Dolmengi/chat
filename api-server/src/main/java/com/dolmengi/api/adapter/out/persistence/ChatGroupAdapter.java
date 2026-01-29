package com.dolmengi.api.adapter.out.persistence;

import static com.dolmengi.common.exception.ExceptionCode.INVALID_PARAMETER;

import com.dolmengi.api.adapter.out.persistence.entity.ChatGroupEntity;
import com.dolmengi.api.adapter.out.persistence.entity.ChatGroupUserEntity;
import com.dolmengi.api.adapter.out.persistence.repository.ChatGroupRepository;
import com.dolmengi.api.adapter.out.persistence.repository.ChatGroupUserRepository;
import com.dolmengi.api.application.port.out.persistence.ChatGroupPort;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupType;
import com.dolmengi.common.exception.ChatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatGroupAdapter implements ChatGroupPort {

    private final ChatGroupRepository repository;
    private final ChatGroupUserRepository groupUserRepository;

    @Override
    public ChatGroup createChatGroup(String name, GroupType type) {
        ChatGroupEntity entity = ChatGroupEntity.builder()
                .name(name)
                .type(type)
                .build();

        return repository.save(entity).toDomain();
    }

    @Override
    public void changeOwner(ChatGroupUser chatGroupUser) {
        ChatGroupUserEntity groupUserEntity = groupUserRepository.findById(chatGroupUser.id()).orElseThrow(() -> new ChatException(INVALID_PARAMETER));
        ChatGroupEntity entity = repository.findById(chatGroupUser.chatGroup().id()).orElseThrow(() -> new ChatException(INVALID_PARAMETER));

        entity.changeOwner(groupUserEntity);
    }

    @Override
    public ChatGroup getChatGroup(Long id) {
        return repository.findById(id).map(ChatGroupEntity::toDomain).orElse(null);
    }

}
