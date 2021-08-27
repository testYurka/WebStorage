package yura.webstorageorders.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yura.webstorageorders.model.Storage;

import java.util.List;

public interface StorageDao extends JpaRepository<Storage, Long> {
    List<Storage> findStorageByItem(String item);
}
