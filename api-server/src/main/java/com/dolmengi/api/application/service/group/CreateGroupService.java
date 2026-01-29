package com.dolmengi.api.application.service.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;
import com.dolmengi.api.application.port.in.group.CreateGroupUseCase;
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
public class CreateGroupService implements CreateGroupUseCase {

    private final ChatGroupPort groupPort;
    private final ChatGroupUserPort groupUserPort;
    private final ChatChannelPort channelPort;

    @Transactional
    @Override
    public CreateGroupResponse create(CreateGroupRequest request) {
        SessionContext sessionContext = HttpServletUtils.getSessionContext();
        Account account = sessionContext.account();

        ChatGroup group = groupPort.createChatGroup(request.name(), GroupType.GROUP);
        ChatChannel channel = channelPort.createDefaultChannel(group);
        ChatGroupUser owner = groupUserPort.createChatGroupUser(group, account, GroupUserType.OWNER);

        groupPort.changeOwner(owner);

        return new CreateGroupResponse(group.id(), owner.id(), group.name(), group.type(), group.description(), List.of(channel));
    }

}
