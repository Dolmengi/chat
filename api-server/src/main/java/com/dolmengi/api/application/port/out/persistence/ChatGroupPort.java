package com.dolmengi.api.application.port.out.persistence;

import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupType;

public interface ChatGroupPort {

    ChatGroup createChatGroup(String name, GroupType type);

    void changeOwner(ChatGroupUser chatGroupUser);

    ChatGroup getChatGroup(Long id);

}
