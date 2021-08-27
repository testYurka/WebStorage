package yura.webstorageorders.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "storages")
@Data
public class Storage implements Comparable<Storage>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private Double price;
    private Long quantity;

    public Storage(String item, Double price, Long quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public Storage() {

    }

    @Override
    public int compareTo(Storage o) {
        return this.price.compareTo(o.getPrice());
    }
}
