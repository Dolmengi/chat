package com.dolmengi.api.adapter.out.persistence.repository;

import com.dolmengi.api.adapter.out.persistence.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

}
