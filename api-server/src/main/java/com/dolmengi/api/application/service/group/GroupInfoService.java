package com.dolmengi.api.application.service.group;

import com.dolmengi.api.adapter.in.rest.group.dto.GroupInfo;
import com.dolmengi.api.application.port.out.persistence.ChatChannelPort;
import com.dolmengi.api.application.port.out.persistence.ChatGroupUserPort;
import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupType;
import com.dolmengi.common.domain.group.Profile;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class GroupInfoService {

    private final ChatGroupUserPort groupUserPort;
    private final ChatChannelPort channelPort;

    GroupInfo groupInfo(Account account, ChatGroupUser chatGroupUser) {
        ChatGroup chatGroup = chatGroupUser.chatGroup();
        List<ChatChannel> chatChannelList = channelPort.getChatChannelList(chatGroup);
        List<Profile> profileList = null;
        String groupName = chatGroup.name();
        if (!Objects.equals(GroupType.GROUP, chatGroup.type())) {
            List<ChatGroupUser> joinedGroupUser = groupUserPort.getChatGroupUserList(chatGroup, account);

            profileList = joinedGroupUser.stream().map(groupUser -> {
                Account joinedAccount = groupUser.account();

                return new Profile(groupUser.id(), joinedAccount.userId(), joinedAccount.name(), groupUser.type());
            }).toList();

            if (Objects.equals(GroupType.DIRECT, chatGroup.type()) && profileList.size() == 1) {
                groupName = profileList.getFirst().name();
            }
        }

        return new GroupInfo(chatGroup.id(), chatGroupUser.id(), groupName, chatGroup.type(), chatGroup.description(), profileList, chatChannelList);
    }

    List<GroupInfo> groupInfoList(Account account, List<ChatGroupUser> chatGroupUsers) {
        return chatGroupUsers.parallelStream().map(groupUser -> this.groupInfo(account, groupUser)).toList();
    }

}
