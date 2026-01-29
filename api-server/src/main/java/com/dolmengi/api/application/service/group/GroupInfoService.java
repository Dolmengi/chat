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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
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

            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                List<CompletableFuture<Profile>> futures = joinedGroupUser.stream().map(groupUser -> CompletableFuture.supplyAsync(() -> {
                            Account joinedAccount = groupUser.account();

                            return new Profile(groupUser.id(), joinedAccount.userId(), joinedAccount.name(), groupUser.type());
                        }, executor)).toList();

                profileList = futures.stream().map(CompletableFuture::join).toList();
            }

            if (Objects.equals(GroupType.DIRECT, chatGroup.type()) && profileList.size() == 1) {
                groupName = profileList.getFirst().name();
            }
        }

        return new GroupInfo(chatGroup.id(), chatGroupUser.id(), groupName, chatGroup.type(), chatGroup.description(), profileList, chatChannelList);
    }

    List<GroupInfo> groupInfoList(Account account, List<ChatGroupUser> chatGroupUsers) {
        return chatGroupUsers.parallelStream().map(groupUser -> this.groupInfo(account, groupUser)).toList();
    }

    private static final Semaphore DB_LIMITER = new Semaphore(10);

    private void dbConn() {
        try {
            DB_LIMITER.acquire(); // 허가증 획득
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            DB_LIMITER.release(); // 허가증 반납
        }
    }

}
