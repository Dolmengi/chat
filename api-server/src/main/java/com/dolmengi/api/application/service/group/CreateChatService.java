package com.dolmengi.api.application.service.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateChatRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;
import com.dolmengi.api.application.port.in.group.CreateChatUseCase;
import com.dolmengi.api.application.port.out.persistence.AccountPort;
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
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateChatService implements CreateChatUseCase {

    private final AccountPort accountPort;
    private final ChatGroupPort groupPort;
    private final ChatGroupUserPort groupUserPort;
    private final ChatChannelPort channelPort;

    @Transactional
    @Override
    public CreateGroupResponse create(CreateChatRequest request) {
        Account account = HttpServletUtils.getSessionAccount();
        GroupType type = GroupType.CHAT;
        if (request.userIds().size() == 1) {
            type = GroupType.DIRECT;
        }

        ChatGroup group = groupPort.createChatGroup(null, type);
        ChatGroupUser owner = groupUserPort.createChatGroupUser(group, account, GroupUserType.OWNER);
        ChatChannel channel = channelPort.createDefaultChannel(group);

        List<String> userIds = request.userIds().stream()
            .filter(StringUtils::hasText)
            .distinct()
            .filter(userId -> !Objects.equals(userId, account.userId()))
            .toList();

        userIds.forEach(userId -> {
            Account chatUser = accountPort.getAccount(userId);
            if (!ObjectUtils.isEmpty(chatUser)) {
                groupUserPort.createChatGroupUser(group, chatUser, GroupUserType.MEMBER);
            }
        });

        groupPort.changeOwner(owner);

        return new CreateGroupResponse(group.id(), owner.id(), group.name(), group.type(), group.description(), List.of(channel));
    }

}
