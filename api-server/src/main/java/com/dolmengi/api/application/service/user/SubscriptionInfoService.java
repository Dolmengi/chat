package com.dolmengi.api.application.service.user;

import static com.dolmengi.common.constants.CacheType.SUBSCRIPTION;

import com.dolmengi.api.application.port.in.user.SubscriptionInfoUseCase;
import com.dolmengi.common.domain.account.SubscriptionInfo;
import com.dolmengi.common.util.CacheUtils;
import com.dolmengi.common.util.GeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubscriptionInfoService implements SubscriptionInfoUseCase {

    @Value("${urls.pub-server}")
    private String pubServerUrl;

    @Override
    public SubscriptionInfo subscriptionInfo() {
        log.info("요청 사용자 확인");

        String token = GeneratorUtils.randomText(32);
        String topic = "/topic/" + GeneratorUtils.randomText(12);

        SubscriptionInfo subscriptionInfo = new SubscriptionInfo(token, pubServerUrl, topic);
        // 로컬 캐시가 아닌 Redis 에서 관리
        CacheUtils.put(SUBSCRIPTION, token, subscriptionInfo);

        return subscriptionInfo;
    }

}
