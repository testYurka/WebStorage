package yura.webstorageorders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yura.webstorageorders.dao.StorageDao;
import yura.webstorageorders.model.Storage;
import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService{
    @Autowired
    private StorageDao storageDao;

    @Override
    public List<Storage> findAll() {
        return storageDao.findAll();
    }

    @Override
    public Optional<Storage> findById(Long id) {
        return storageDao.findById(id);
    }

    @Override
    public Storage save(Storage storage) {
        return storageDao.save(storage);
    }

    @Override
    public void delete(Long storageId) {
        storageDao.deleteById(storageId);
    }

    @Override
    public Storage update(Storage storage, Long storageId) {
        Optional<Storage> oldStorage = storageDao.findById(storageId);
        oldStorage.ifPresent(value -> storage.setId(value.getId()));
        return storageDao.save(storage);
    }
}
