package com.jk.learn.learnjpa.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;

@Entity
@Table(name = "ORDERX")
@DynamicInsert
@DynamicUpdate
@Getter
public class OrderEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<OrderItemEntity> orderItems = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.order")
	private Set<OrderStageEntity> stages = new HashSet<>();

}
