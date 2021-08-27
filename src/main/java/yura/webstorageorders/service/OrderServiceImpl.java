package yura.webstorageorders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import yura.webstorageorders.dao.OrderDao;
import yura.webstorageorders.dao.StorageDao;
import yura.webstorageorders.model.Order;
import yura.webstorageorders.model.Storage;

import java.util.*;

@Service
@EnableScheduling
public class OrderServiceImpl implements OrderService{
    private static List<Long> quantityList = new ArrayList<Long>();
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private StorageService storageService;


    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public Order save(Order order) {
        Order inhOrder = orderDao.save(order);
        update(order);
        return inhOrder;
    }

    @Override
    public void delete(Long orderId) {
        orderDao.deleteById(orderId);
    }

    @Override
    public Order update(Order order) {
        List<Storage> storageByItem = storageDao.findStorageByItem(order.getItem());
        Collections.sort(storageByItem);
        if (storageByItem.size() == 0) {
            order.setQuantity(0L);
            order.setPrice(0.0);
            delete(order.getId());
            orderDao.save(order);
            return order;
        }
        Storage storage = storageByItem.get(0);
        order.setPrice(storage.getPrice());
        if (order.getQuantity() > storage.getQuantity()) {
            order.setQuantity(storage.getQuantity());
        }
        quantityList.add(storage.getId());
        quantityList.add(order.getQuantity());
        delete(order.getId());
        orderDao.save(order);
        storage.setQuantity(storage.getQuantity() - order.getQuantity());
        storageService.update(storage, storage.getId());
       return order;
    }

    public void deleteAll() {
        orderDao.deleteAll();
    }

    @Scheduled(fixedDelay=600000)
    public void deleteOrder() {
        orderDao.deleteAll();
        if (quantityList.isEmpty()) {
            return;
        }
      for(int i = 0; i < quantityList.size(); i += 2){
          Storage storage = storageDao.findById(quantityList.get(i)).get();
          storage.setQuantity(storage.getQuantity() + quantityList.get(i + 1));
          storageService.update(storage, storage.getId());
      }
      quantityList.clear();
    }
}
