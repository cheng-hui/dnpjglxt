package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.dto.StorageDTO;
import com.zhannicholas.cpwms.domain.model.Storage;
import com.zhannicholas.cpwms.domain.repository.PartsRepository;
import com.zhannicholas.cpwms.domain.repository.StorageRepository;
import com.zhannicholas.cpwms.service.StorageService;
import com.zhannicholas.cpwms.util.ToMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final PartsRepository partsRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository, PartsRepository partsRepository) {
        this.storageRepository = storageRepository;
        this.partsRepository = partsRepository;
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
            if(!isValidId(partsId, repoId)){
                storageRepository.saveStorage(partsId, repoId, number);
            }
            else{
                storageRepository.changeStorage(partsId, repoId, number);
            }
        }
        return true;
    }

    // 保存库存一条库存记录
    @Override
    public boolean saveStorage(int partsId, int repoId, int number) {
        if(!isValidId(partsId, repoId)){
            storageRepository.saveStorage(partsId, repoId, number);
            return true;
        }
        return false;
    }

    // 修改库存记录
    @Override
    public boolean updateStorage(int partsId, int repoId, int number) {
        if(isValidId(partsId, repoId)){
            storageRepository.updateStorage(partsId, repoId, number);
            return true;
        }
        return false;
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
            storageRepository.changeStorage(partsId, repoId, -number);
        }
        return true;
    }

    // 查询所有库存记录---分页查询
    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        System.out.println("查询所有库存记录：\n" + storageRepository.findAllStorageDTO(pageable).getContent());
        return storageDTOPage2Map(storageRepository.findAllStorageDTO(pageable));
    }

    // 查询某个仓库中所有的库存记录---分页查询
    @Override
    public Map<String, Object> findAll(int repoId, Pageable pageable) {
        return storageDTOPage2Map(storageRepository.findAllStorageDTO(repoId, pageable));
    }

    // 查询单条记录
    @Override
    public Map<String, Object> findByPartsIdAndRepoId(int partsId, int repoId) {
        return ToMapUtil.fromAInstance(storageRepository.findStorageDTO(partsId, repoId));
    }

    @Override
    public Map<String, Object> findByPartsId(int partsId, Pageable pageable) {
        return storageDTOPage2Map(storageRepository.findStorageDTO(partsId, pageable));
    }

    @Override
    public Map<String, Object> findByPartsName(int repoId, String partsName, Pageable pageable) {
        return storageDTOPage2Map(storageRepository.findUsingPartsName(repoId, partsName, pageable));
    }

    @Override
    public Map<String, Object> findByPartsName(String partsName, Pageable pageable) {
        return storageDTOPage2Map(storageRepository.findUsingPartsName(partsName, pageable));
    }

    @Override
    public Map<String, Object> findByPartsType(int repoId, String partsType, Pageable pageable) {
        return storageDTOPage2Map(storageRepository.findUsingPartsType(repoId, partsType, pageable));
    }

    @Override
    public Map<String, Object> findByPartsType(String partsType, Pageable pageable) {
        return storageDTOPage2Map(storageRepository.findUsingPartsType(partsType, pageable));
    }

    @Override
    public boolean delete(int partsId, int repoId) {
        if(isValidId(partsId, repoId)){
            storageRepository.delete(partsId, repoId);
            return true;
        }
        return false;
    }


    // 将 Storage 实体类对象转为相应的数据传输对象，用于页面显示
    private StorageDTO storage2StorageDTO(Storage storage){
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setPartsId(storage.getPartsId());
        storageDTO.setPartsName(storage.getParts().getPartsName());
        storageDTO.setPartsType(storage.getParts().getPartsType());
        storageDTO.setRepoId(storage.getRepoId());
        storageDTO.setNumber(storage.getNumber());
        return storageDTO;
    }

    // 将 Storage 信息页转为相应的 Map 对象
    private Map<String, Object> storagePage2Map(Page<Storage> storagePage){
        List<Storage> storageList = storagePage.getContent();
        return storageList2Map(storageList);
    }

    // 将 Storage 信息页转为相应的 Map 对象
    private Map<String, Object> storageList2Map(List<Storage> storageList){
        List<StorageDTO> storageDTOList = new ArrayList<>();
        for(Storage storage: storageList){
            storageDTOList.add(storage2StorageDTO(storage));
        }

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", storageDTOList);
        resultSet.put("total", storageDTOList.size());
        return resultSet;
    }

    // 将 StorageDTO 信息页转为相应的 Map 对象
    private Map<String, Object> storageDTOPage2Map(Page<StorageDTO> storageDTOPage){
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", storageDTOPage.getContent());
        resultSet.put("total", storageDTOPage.getTotalElements());
        return resultSet;
    }

    private boolean isValidId(int partsId, int repoId){
        return storageRepository.findByPartsIdAndRepoId(partsId, repoId) != null;
    }
}
