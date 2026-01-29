package com.dolmengi.api.application.service.channel;

import com.dolmengi.api.adapter.in.rest.channel.dto.UpdateChannelRequest;
import com.dolmengi.api.adapter.in.rest.channel.dto.UpdateChannelResponse;
import com.dolmengi.api.application.port.in.channel.UpdateChannelUseCase;
import com.dolmengi.api.application.port.out.persistence.ChatChannelPort;
import com.dolmengi.api.application.port.out.persistence.ChatGroupUserPort;
import com.dolmengi.api.commons.util.HttpServletUtils;
import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.channel.ChannelType;
import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupUserType;
import com.dolmengi.common.exception.ChatException;
import com.dolmengi.common.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateChannelService implements UpdateChannelUseCase {

    private final ChatGroupUserPort chatGroupUserPort;
    private final ChatChannelPort chatChannelPort;

    @Transactional
    @Override
    public UpdateChannelResponse update(UpdateChannelRequest request) {
        Account account = HttpServletUtils.getSessionAccount();

        ChatGroupUser chatGroupUser = chatGroupUserPort.getChatGroupUser(request.groupId(), account.id());
        if (ObjectUtils.isEmpty(chatGroupUser)) {
            throw new ChatException(ExceptionCode.VALIDATION_FAILED);
        }
        if (GroupUserType.OWNER != chatGroupUser.type()) {
            throw new ChatException(ExceptionCode.VALIDATION_FAILED);
        }

        ChatChannel chatChannel = chatChannelPort.getChatChannel(request.channelId());
        if (ObjectUtils.isEmpty(chatChannel)) {
            throw new ChatException(ExceptionCode.VALIDATION_FAILED);
        }

        ChannelType type = chatChannel.type().resolve(request.isPrivate());

        chatChannel = chatChannelPort.updateChannel(chatChannel.id(), request.name(), type);

        return new UpdateChannelResponse(chatChannel.id(), chatChannel.groupId(), chatChannel.name(), chatChannel.type());
    }

}
