package com.dolmengi.common.domain.group;

public record ChatGroup(Long id, Long ownerId, String name, GroupType type, String description) {

}
