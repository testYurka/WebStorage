package yura.webstorageorders.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yura.webstorageorders.model.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
}
