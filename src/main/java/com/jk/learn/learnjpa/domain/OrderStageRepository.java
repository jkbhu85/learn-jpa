package com.jk.learn.learnjpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStageRepository extends JpaRepository<OrderStageEntity, OrderStageId> {

}
