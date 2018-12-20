package com.zhannicholas.cpwms.service;

import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface StorageService {
    Map<String, Object> findNumberById(int partsId, int repoId);
    boolean increaseStorage(int partsId, int repoId, int number);
    boolean saveStorage(int partsId, int repoId, int number);
    boolean updateStorage(int partsId, int repoId, int number);
    boolean decreaseStorage(int partsId, int repoId, int number);
    Map<String, Object> findAll(Pageable pageable);
    Map<String, Object>findAll(int repoId, Pageable pageable);
    Map<String, Object> findByPartsIdAndRepoId(int partsId, int repoId);
    Map<String, Object> findByPartsId(int partsId, Pageable pageable);
    Map<String, Object> findByPartsName(int repoId, String partsName, Pageable pageable);
    Map<String, Object> findByPartsName(String partsName, Pageable pageable);
    Map<String, Object> findByPartsType(int repoId, String partsType, Pageable pageable);
    Map<String, Object> findByPartsType(String partsType, Pageable pageable);
    boolean delete(int partsId, int repoId);
}
