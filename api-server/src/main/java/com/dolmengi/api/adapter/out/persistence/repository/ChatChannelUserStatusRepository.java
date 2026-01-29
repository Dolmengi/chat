package com.dolmengi.api.adapter.out.persistence.repository;

import com.dolmengi.api.adapter.out.persistence.entity.ChatChannelUserStatusEntity;
import com.dolmengi.api.adapter.out.persistence.entity.pk.ChatChannelUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatChannelUserStatusRepository extends JpaRepository<ChatChannelUserStatusEntity, ChatChannelUserId> {

}
