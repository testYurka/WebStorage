package yura.webstorageorders.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private Double price;
    private  Long quantity;

    public Order() {
    }

    public Order(String item, Long quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
