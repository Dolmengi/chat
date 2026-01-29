package com.dolmengi.api.application.port.out.persistence;

import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupUserType;
import java.util.List;

public interface ChatGroupUserPort {

    ChatGroupUser createChatGroupUser(ChatGroup chatGroup, Account account, GroupUserType type);

    ChatGroupUser getChatGroupUser(Long id);

    List<ChatGroupUser> getChatGroupUserList(Account account);

    List<ChatGroupUser> getChatGroupUserList(ChatGroup chatGroup);

    List<ChatGroupUser> getChatGroupUserList(ChatGroup chatGroup, Account account);

}
