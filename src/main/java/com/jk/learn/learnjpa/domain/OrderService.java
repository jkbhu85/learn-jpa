package com.jk.learn.learnjpa.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderService {

	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;
	private OrderStageRepository orderStageRepository;

	@Transactional(rollbackOn = Throwable.class)
	public Optional<OrderEntity> findOrderById(Long id) {
		return orderRepository.findById(id);
	}

	@Transactional(rollbackOn = Throwable.class)
	public OrderEntity save(OrderEntity entity, List<OrderItemEntity> orderItems,
			OrderStageEntity orderStage) {
		OrderEntity savedOrder = orderRepository.save(entity);

		List<OrderItemEntity> updatedOrderItems = orderItems.stream()
				.map(e -> e.toBuilder().order(savedOrder).build())
				.collect(Collectors.toList());
		OrderStageEntity updatedOrderStage = new OrderStageEntity(savedOrder,
				orderStage.getStatus());

		orderItemRepository.saveAll(updatedOrderItems);
		orderStageRepository.save(updatedOrderStage);
		return savedOrder;
	}

}
