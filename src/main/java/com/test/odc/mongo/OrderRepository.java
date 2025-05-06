package com.test.odc.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.odc.entities.Order;

public interface OrderRepository extends MongoRepository<Order, String>{
	Page<Order> findByIdUsuario(String idUsuario, Pageable pageable);
}
