package com.dolmengi.api.adapter.in.rest.channel.dto;

import com.dolmengi.common.domain.channel.ChannelType;

public record CreateChannelResponse(Long id, Long groupId, String name, ChannelType type) {

}
