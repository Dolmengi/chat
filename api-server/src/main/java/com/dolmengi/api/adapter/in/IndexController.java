package com.dolmengi.api.adapter.in;

import com.dolmengi.common.constants.CacheType;
import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.common.util.CacheUtils;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {

    @GetMapping
    public ResponseEntity<String> index(@RequestParam String token) {
        SubscriptionInfo aaa = new SubscriptionInfo(token, "https://", "/topic");
        CacheUtils.put(CacheType.SUBSCRIPTION, token, aaa);
        CacheUtils.put(CacheType.SESSION, Integer.valueOf(token), "fwfwqqwerrrrqwer");

        Map<Object, Object> map = CacheUtils.getAll(CacheType.SUBSCRIPTION);
        log.error(">> all1: {}", map);
        Map<Object, Object> map1 = CacheUtils.getAll(CacheType.SESSION);
        log.error(">> all1: {}", map1);

        log.error(">> all2: {}", CacheUtils.getStats(CacheType.SUBSCRIPTION));
        log.error(">> all2: {}", CacheUtils.getStats(CacheType.SESSION));

        return ResponseEntity.ok().body("I am Chat Api Server");
    }

    @GetMapping("/health/check")
    public ResponseEntity<Void> healthCheck(@RequestParam String token) {
        SubscriptionInfo subscriptionInfo = CacheUtils.get(CacheType.SUBSCRIPTION, token);
        log.error(">> subscriptionInfo: {}", subscriptionInfo);

        Map<Object, Object> map = CacheUtils.getAll(CacheType.SUBSCRIPTION);
        log.error(">> all2: {}", map);
        Map<Object, Object> map1 = CacheUtils.getAll(CacheType.SESSION);
        log.error(">> all2: {}", map1);

        log.error(">> all2: {}", CacheUtils.getStats(CacheType.SUBSCRIPTION));
        log.error(">> all2: {}", CacheUtils.getStats(CacheType.SESSION));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
