package com.dolmengi.api.adapter.out.persistence.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class ChatGroupUserId {

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "accountId")
    private Long accountId;

}
