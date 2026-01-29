package com.dolmengi.api.application.service.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateChatRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;
import com.dolmengi.api.application.port.in.group.CreateChatUseCase;
import com.dolmengi.api.application.port.out.persistence.ChatChannelPort;
import com.dolmengi.api.application.port.out.persistence.ChatGroupPort;
import com.dolmengi.api.application.port.out.persistence.ChatGroupUserPort;
import com.dolmengi.api.commons.util.HttpServletUtils;
import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupType;
import com.dolmengi.common.domain.group.GroupUserType;
import com.dolmengi.common.domain.security.SessionContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateChatService implements CreateChatUseCase {

    private final ChatGroupPort groupPort;
    private final ChatGroupUserPort groupUserPort;
    private final ChatChannelPort channelPort;

    @Transactional
    @Override
    public CreateGroupResponse create(CreateChatRequest request) {
        SessionContext sessionContext = HttpServletUtils.getSessionContext();
        Account account = sessionContext.account();
        GroupType type = GroupType.CHAT;
        if (request.userIds().size() == 1) {
            type = GroupType.DIRECT;
        }

        ChatGroup group = groupPort.createChatGroup(null, type);
        ChatChannel channel = channelPort.createDefaultChannel(group);
        ChatGroupUser owner = groupUserPort.createChatGroupUser(group, account, GroupUserType.OWNER);
        request.userIds().forEach(userId -> {

        });

        groupPort.changeOwner(owner);

        return new CreateGroupResponse(group.id(), owner.id(), group.name(), group.type(), group.description(), List.of(channel));
    }

}
