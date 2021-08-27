package yura.webstorageorders.service;

import yura.webstorageorders.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order save(Order order);

    void delete(Long orderId);

    Order update(Order order);

    void deleteAll();
 }
