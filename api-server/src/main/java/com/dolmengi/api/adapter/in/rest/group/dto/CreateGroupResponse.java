package com.dolmengi.api.adapter.in.rest.group.dto;

import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.GroupType;
import java.util.List;

public record CreateGroupResponse(Long groupId, Long ownerId, String name, GroupType type, String description, List<ChatChannel> channels) {

}
