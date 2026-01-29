package com.dolmengi.api.adapter.out.persistence;

import static com.dolmengi.common.exception.ExceptionCode.INVALID_PARAMETER;

import com.dolmengi.api.adapter.out.persistence.entity.ChatChannelEntity;
import com.dolmengi.api.adapter.out.persistence.entity.ChatGroupEntity;
import com.dolmengi.api.adapter.out.persistence.repository.ChatChannelRepository;
import com.dolmengi.api.adapter.out.persistence.repository.ChatGroupRepository;
import com.dolmengi.api.application.port.out.persistence.ChatChannelPort;
import com.dolmengi.common.domain.channel.ChannelType;
import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.exception.ChatException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatChannelAdapter implements ChatChannelPort {

    private final ChatChannelRepository repository;
    private final ChatGroupRepository groupRepository;

    @Override
    public ChatChannel createDefaultChannel(ChatGroup chatGroup) {
        ChatGroupEntity groupEntity = groupRepository.findById(chatGroup.id()).orElseThrow(() -> new ChatException(INVALID_PARAMETER));
        ChatChannelEntity entity = ChatChannelEntity.builder()
                .chatGroup(groupEntity)
                .name("일반")
                .type(ChannelType.DEFAULT)
                .build();

        return repository.save(entity).toDomain();
    }

    @Override
    public ChatChannel getChatChannel(Long id) {
        return repository.findById(id).map(ChatChannelEntity::toDomain).orElse(null);
    }

    @Override
    public List<ChatChannel> getChatChannelList(ChatGroup chatGroup) {
        List<ChatChannelEntity> entities = repository.findByChatGroup(chatGroup.id());

        return entities.stream().map(ChatChannelEntity::toDomain).toList();
    }

}
