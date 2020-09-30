package com.jk.learn.learnjpa.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class OrderServiceTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderStatusService orderStatusService;

	@Autowired
	private OrderService orderService;

	@Test
	void testOrder() throws Exception {
		OrderEntity order = createAndSaveOrder();
		assertNotNull(order.getId());

		log.info("Finding order with id: {}", order.getId());
		OrderEntity order2 = orderService.findOrderById(order.getId()).orElse(null);
		assertNotNull(order2);
		assertEquals(order.getId(), order2.getId());
		assertEquals(1, order2.getStages().size());
		assertEquals(3, order2.getOrderItems().size());
		
		log.info("Printing order as JSON");
		ObjectMapper oMapper = new ObjectMapper();
		log.info(oMapper.writeValueAsString(order2));
	}

	OrderEntity createAndSaveOrder() {
		OrderEntity order = new OrderEntity();
		return orderService.save(order, createOrderItems(), createOrderStage(order));
	}

	OrderStageEntity createOrderStage(OrderEntity order) {
		OrderStatusEntity statusEntity = Objects.requireNonNull(orderStatusService
				.getOrderStatusEnumEntityMap().get(OrderStatus.PAYMENT_PENDING));
		OrderStageEntity stage = new OrderStageEntity(order, statusEntity);
		return stage;
	}

	List<OrderItemEntity> createOrderItems() {
		Long[][] itemList = {
				{ 1L, 1L },
				{ 3L, 2L },
				{ 4L, 1L }
		};
		List<Long> productIds = Stream.of(itemList).map(e -> e[0]).collect(Collectors.toList());
		Map<Long, ProductEntity> products = productRepository.findByIdIn(productIds)
				.stream()
				.collect(Collectors.toMap(ProductEntity::getId, e -> e));

		assertEquals(productIds.size(), products.size());

		List<OrderItemEntity> orderItems = new ArrayList<>(products.size());
		for (Long[] itemInfo : itemList) {
			ProductEntity product = products.get(itemInfo[0]);
			OrderItemEntity orderItem = OrderItemEntity.builder()
					.product(product)
					.count(itemInfo[1].intValue())
					.price(product.getPrice())
					.build();
			orderItems.add(orderItem);
		}
		return orderItems;
	}

}
