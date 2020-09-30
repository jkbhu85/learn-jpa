package com.jk.learn.learnjpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Immutable;

import lombok.Getter;

@Getter
@Entity
@Table(name = "ORDER_STATUS")
@Immutable
@DynamicInsert
public class OrderStatusEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "STATUS_NAME", length = 20, nullable = false, unique = true)
	private String statusName;

}
