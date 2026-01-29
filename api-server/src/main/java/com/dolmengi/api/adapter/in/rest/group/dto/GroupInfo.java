package com.dolmengi.api.adapter.in.rest.group.dto;

import com.dolmengi.common.domain.channel.ChatChannel;
import com.dolmengi.common.domain.group.GroupType;
import com.dolmengi.common.domain.group.Profile;
import java.util.List;

public record GroupInfo(Long groupId, Long ownerId, String name, GroupType type, String description, List<Profile> profileList, List<ChatChannel> channelList) {

}
