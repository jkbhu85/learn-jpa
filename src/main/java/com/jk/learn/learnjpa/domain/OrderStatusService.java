package com.jk.learn.learnjpa.domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderStatusService {

	private OrderStatusRepository repository;

	public Map<OrderStatus, OrderStatusEntity> getOrderStatusEnumEntityMap() {
		Map<OrderStatus, OrderStatusEntity> map = new EnumMap<>(OrderStatus.class);

		Map<String, OrderStatusEntity> nameEntityMap = repository.findAll().stream()
				.collect(Collectors.toMap(OrderStatusEntity::getStatusName, e -> e));

		for (OrderStatus e : OrderStatus.values()) {
			OrderStatusEntity statusEntity = Objects
					.requireNonNull(nameEntityMap.get(e.toString()));
			map.put(e, statusEntity);
		}

		return map;
	}

}
