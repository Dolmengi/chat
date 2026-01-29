package com.dolmengi.api.application.port.out.persistence;

import com.dolmengi.common.domain.channel.ChannelType;
import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.ChatGroup;
import java.util.List;

public interface ChatChannelPort {

    ChatChannel createDefaultChannel(ChatGroup chatGroup);

    ChatChannel createChannel(ChatGroup chatGroup, String name, ChannelType type);

    ChatChannel updateChannel(Long id, String name, ChannelType type);

    void deleteChannel(Long id);

    ChatChannel getChatChannel(Long id);

    List<ChatChannel> getChatChannelList(ChatGroup chatGroup);

}
