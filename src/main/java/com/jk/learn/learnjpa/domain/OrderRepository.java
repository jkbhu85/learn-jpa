package com.jk.learn.learnjpa.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	@EntityGraph(attributePaths = { "stages", "orderItems" })
	Optional<OrderEntity> findById(Long id);

}
