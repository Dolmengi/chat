package com.dolmengi.common.domain.channel;

import java.util.Set;

public enum ChannelType {
    DEFAULT, PUBLIC, PRIVATE, SYSTEM;

    private static final Set<ChannelType> USER_UPDATABLE_TYPES = Set.of(PUBLIC, PRIVATE);

    public boolean isUpdatable() {
        return USER_UPDATABLE_TYPES.contains(this);
    }

    public ChannelType resolve(Boolean isPrivate) {
        if (isPrivate == null || !isUpdatable()) {
            return this;
        }

        return isPrivate ? PRIVATE : PUBLIC;
    }

}
