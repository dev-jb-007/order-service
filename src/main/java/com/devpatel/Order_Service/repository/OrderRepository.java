package com.devpatel.Order_Service.repository;

import com.devpatel.Order_Service.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {

}
