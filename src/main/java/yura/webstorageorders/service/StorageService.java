package yura.webstorageorders.service;

import yura.webstorageorders.model.Storage;
import java.util.List;
import java.util.Optional;

public interface StorageService {
    List<Storage> findAll();

    Optional<Storage> findById(Long id);

    Storage save(Storage storage);

    void delete(Long storageId);

    Storage update(Storage storage, Long storageId);
}
