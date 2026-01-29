package com.dolmengi.common.constants;

import java.time.Duration;
import lombok.Getter;

@Getter
public enum CacheType {

    SESSION(Duration.ofDays(15)),
    SUBSCRIPTION(Duration.ofMinutes(1)),
    ;

    private final Duration ttl;

    CacheType(Duration ttl) {
        this.ttl = ttl;
    }

}
