package com.dolmengi.api.commons.jpa;

import io.hypersistence.tsid.TSID;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.EnumSet;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;

@Slf4j
public class TsidGenerator implements BeforeExecutionGenerator {

    // 8: 256, 10, 1024, 12: 4096
    private static final int NODE_BITS = 10;
    private static final int MAX_NODE = 1 << NODE_BITS;
    private static final String ZONE_ID = "Asia/Seoul";
    private static final TSID.Factory TSID_FACTORY;

    static {
        int nodeId = generateNodeIdFromHostname();
        log.warn("TSID Generator initialized with Node ID: {} (NodeBits: {})", nodeId, NODE_BITS);

        TSID_FACTORY = TSID.Factory.builder()
                .withRandomFunction(TSID.Factory.THREAD_LOCAL_RANDOM_FUNCTION)
                .withNodeBits(NODE_BITS)
                .withNode(nodeId)
                .withClock(Clock.system(ZoneId.of(ZONE_ID)))
                .build();
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return (currentValue != null) ? currentValue : TSID_FACTORY.generate().toLong();
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EventTypeSets.INSERT_ONLY;
    }

    private static int generateNodeIdFromHostname() {
        try {
            return (InetAddress.getLocalHost().getHostName().hashCode() & Integer.MAX_VALUE) % MAX_NODE;
        } catch (UnknownHostException e) {
            return (int) (Math.random() * MAX_NODE);
        }
    }

    public static LocalDateTime getCreatedAt(Long id) {
        TSID tsid = TSID.from(id);
        Instant instant = tsid.getInstant();

        return LocalDateTime.ofInstant(instant, ZoneId.of(ZONE_ID));
    }

}
