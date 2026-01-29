package com.dolmengi.common.domain.message;

import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.ChatGroupUser;

public record ChatMessage(Long id, ChatChannel chatChannel, ChatGroupUser chatGroupUser, MessageType type, String content, String optional) {

}
