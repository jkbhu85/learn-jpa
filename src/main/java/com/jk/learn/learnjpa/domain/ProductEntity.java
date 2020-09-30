package com.jk.learn.learnjpa.domain;

import java.math.BigDecimal;

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
@Table(name = "PRODUCT")
@DynamicInsert
@Immutable
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE", length = 100, nullable = false)
	private String title;

	@Column(name = "PRICE", nullable = false, precision = 7, scale = 2)
	private BigDecimal price;

}
