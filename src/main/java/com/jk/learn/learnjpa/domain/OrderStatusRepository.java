package com.jk.learn.learnjpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository
		extends JpaRepository<OrderStatusEntity, Long> {
}
