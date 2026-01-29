package com.dolmengi.common.domain.channel;

public record ChatChannel(Long id, Long groupId, String name, ChannelType type) {

}
