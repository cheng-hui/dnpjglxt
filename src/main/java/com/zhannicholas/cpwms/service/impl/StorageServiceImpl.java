package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Storage;
import com.zhannicholas.cpwms.domain.repository.StorageRepository;
import com.zhannicholas.cpwms.service.StorageService;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    /**
     * 查找某一仓库中某一配件的数量
     * @param partsId   配件Id
     * @param repoId    仓库Id
     * @return repoId 对应的仓库中 parts 的数量
     */
    @Override
    public Map<String, Object> findNumberById(int partsId, int repoId) {
        return ToMapUtil.fromString(String.valueOf(storageRepository.findNumberById(partsId, repoId)));
    }

    /**
     * 为指定配件增加库存
     * @param partsId   配件Id
     * @param repoId    仓库Id
     * @param number    增加的配件数量
     * @return  返回一个 boolean 值，若值为true表示数目增加成功，否则表示增加失败
     */
    @Override
    public boolean increaseStorage(int partsId, int repoId, int number) {
        if(number < 0) {
            return false;
        }
        synchronized (this){
            Storage storage = storageRepository.findByPartsIdAndRepoId(partsId, repoId);
            if(storage == null){
                Storage newStorage = new Storage();
                newStorage.setPartsId(partsId);
                newStorage.setRepoId(repoId);
                newStorage.setNumber(number);
                storageRepository.saveAndFlush(newStorage);
            }
            else{
                storageRepository.updateStorage(partsId, repoId, number);
            }
        }
        return true;
    }

    /**
     * 为指定配件减少库存
     * @param partsId   配件Id
     * @param repoId    仓库Id
     * @param number    减少的配件数量
     * @return  返回一个 boolean 值，若值为true表示数目增加成功，否则表示增加失败
     */
    @Override
    public boolean decreaseStorage(int partsId, int repoId, int number) {
        Storage storage = storageRepository.findByPartsIdAndRepoId(partsId, repoId);
        synchronized (this) {
            if (storage == null) {
                return false;
            } else if (storage.getNumber() < number || number < 0) {
                return false;
            }
            storageRepository.updateStorage(partsId, repoId, -number);
        }
        return true;
    }
}
