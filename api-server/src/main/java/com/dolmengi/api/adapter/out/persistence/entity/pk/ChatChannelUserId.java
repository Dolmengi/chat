package com.dolmengi.api.adapter.out.persistence.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class ChatChannelUserId {

    @Column(name = "channelId")
    private Long channelId;

    @Column(name = "accountId")
    private Long accountId;

}
