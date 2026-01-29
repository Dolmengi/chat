package com.dolmengi.api.application.service.channel;

import com.dolmengi.api.adapter.in.rest.channel.dto.CreateChannelRequest;
import com.dolmengi.api.adapter.in.rest.channel.dto.CreateChannelResponse;
import com.dolmengi.api.application.port.in.channel.CreateChannelUseCase;
import com.dolmengi.api.application.port.out.persistence.ChatChannelPort;
import com.dolmengi.api.application.port.out.persistence.ChatGroupPort;
import com.dolmengi.api.application.port.out.persistence.ChatGroupUserPort;
import com.dolmengi.api.commons.util.HttpServletUtils;
import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.channel.ChannelType;
import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupUserType;
import com.dolmengi.common.exception.ChatException;
import com.dolmengi.common.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateChannelService implements CreateChannelUseCase {

    private final ChatGroupPort chatGroupPort;
    private final ChatGroupUserPort chatGroupUserPort;
    private final ChatChannelPort chatChannelPort;

    @Transactional
    @Override
    public CreateChannelResponse create(CreateChannelRequest request) {
        Account account = HttpServletUtils.getSessionAccount();

        ChatGroup chatGroup = chatGroupPort.getChatGroup(request.groupId());
        ChatGroupUser chatGroupUser = chatGroupUserPort.getChatGroupUser(chatGroup.id(), account.id());
        if (GroupUserType.OWNER != chatGroupUser.type()) {
            throw new ChatException(ExceptionCode.VALIDATION_FAILED);
        }

        ChannelType type = ChannelType.PUBLIC;
        if (request.isPrivate()) {
            type = ChannelType.PRIVATE;
        }

        ChatChannel chatChannel = chatChannelPort.createChannel(chatGroup, request.name(), type);

        return new CreateChannelResponse(chatChannel.id(), chatChannel.groupId(), chatChannel.name(), chatChannel.type());
    }

}
