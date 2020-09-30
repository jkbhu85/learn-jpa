package com.jk.learn.learnjpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Immutable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "ORDER_STAGE")
@Immutable
@DynamicInsert
public class OrderStageEntity {

	@EmbeddedId
	private OrderStageId id;

	@Column(name = "CREATED_ON", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdOn;

	public OrderStageEntity(OrderEntity order, OrderStatusEntity status) {
		this.id = new OrderStageId(order, status);
	}

	public OrderStatusEntity getStatus() {
		return this.id.getStatus();
	}

}
