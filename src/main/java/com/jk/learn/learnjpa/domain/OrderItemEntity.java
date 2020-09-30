package com.jk.learn.learnjpa.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Immutable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "ORDER_ITEM")
@DynamicInsert
@Immutable
public class OrderItemEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * Fetch type is EAGER so that product field is not empty when this entity is
	 * loaded. This entity is not loaded by itself. Rather, it is loaded when we
	 * load entity for order with all its information.
	 */
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private ProductEntity product;

	@Column(name = "COUNT", nullable = false)
	private int count;

	@Column(name = "PRICE", nullable = false, precision = 7, scale = 2)
	private BigDecimal price;

	@Getter(AccessLevel.NONE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private OrderEntity order;

}
