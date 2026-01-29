package com.dolmengi.api.adapter.out.listener;

import com.dolmengi.common.domain.message.MessageEvent;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageEventPublisher {

    private static final String bindingName = "publishMessage-out-0";

    private Tracer tracer;
    private final StreamBridge streamBridge;

    @Async
    @EventListener
    public void handle(MessageEvent event) {
        String key = "channel-1";
        //String traceId = tracer.currentSpan().context().traceId();
        String traceId = MDC.get("traceId");
        String spanId = MDC.get("spanId");
        log.error(">>> traceId: {}, spanId: {}", traceId, spanId);

        streamBridge.send(bindingName, MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, key)
                .setHeader("traceparent", "00-aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa-bbbbbbbbbbbbbbbb-01")
                .build());
    }

}
