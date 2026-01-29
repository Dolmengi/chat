package com.dolmengi.api.application.service.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;
import com.dolmengi.api.application.port.in.group.CreateGroupUseCase;
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
import com.dolmengi.common.exception.ChatException;
import com.dolmengi.common.exception.ExceptionCode;
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
public class CreateGroupService implements CreateGroupUseCase {

    private final AccountPort accountPort;
    private final ChatGroupPort groupPort;
    private final ChatGroupUserPort groupUserPort;
    private final ChatChannelPort channelPort;

    @Transactional
    @Override
    public CreateGroupResponse create(CreateGroupRequest request) {
        Account account = HttpServletUtils.getSessionAccount();

        if (!StringUtils.hasText(request.name())) {
            throw new ChatException(ExceptionCode.MISSING_PARAMETER);
        }

        ChatGroup group = groupPort.createChatGroup(request.name(), GroupType.GROUP);
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
