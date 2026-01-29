package com.dolmengi.api.adapter.in.rest.channel.dto;

public record CreateChannelRequest(Long groupId, String name, Boolean isPrivate) {

}
