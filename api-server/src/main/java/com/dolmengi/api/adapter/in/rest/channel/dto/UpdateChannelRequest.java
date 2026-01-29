package com.dolmengi.api.adapter.in.rest.channel.dto;

public record UpdateChannelRequest(Long groupId, Long channelId, String name, Boolean isPrivate) {

}
