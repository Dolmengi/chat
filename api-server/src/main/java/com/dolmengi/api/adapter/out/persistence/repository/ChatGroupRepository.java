package com.dolmengi.api.adapter.out.persistence.repository;

import com.dolmengi.api.adapter.out.persistence.entity.ChatGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroupEntity, Long> {

}
