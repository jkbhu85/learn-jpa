package com.jk.learn.learnjpa.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Immutable
public class OrderStageId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private OrderEntity order;

	/*
	 * Fetch type is EAGER so that status field is not empty when this entity is
	 * loaded. This entity is not loaded by itself. Rather, it is loaded when we
	 * load entity for order with all its information.
	 */
	@Getter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATUS_ID", nullable = false)
	private OrderStatusEntity status;

	@Override
	public int hashCode() {
		int hashCode = 0;
		if (order != null && order.getId() != null) {
			hashCode += order.getId() * 31;
		}
		if (status != null && status.getId() != null) {
			hashCode += status.getId() * 31;
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		OrderStageId other = (OrderStageId) obj;

		boolean orderEquals = (this.order == null && other.order == null)
				|| (this.order.getId() == null && other.order.getId() == null)
				|| (this.order.getId() != null && this.order.getId().equals(other.order.getId()));

		boolean statusEquals = (this.status == null && other.status == null)
				|| (this.status.getId() == null && other.status.getId() == null)
				|| (this.status.getId() != null
						&& this.status.getId().equals(other.status.getId()));

		return orderEquals && statusEquals;
	}

}
