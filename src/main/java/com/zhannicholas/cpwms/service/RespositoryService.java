package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Respository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RespositoryService {
    Map<String, Object> findAll(Pageable pageable);
    boolean save(Respository respository);
    Map<String, Object> findOne(int repoId);
    Map<String, Object> findByRepoAddressContaining(String repoAddress, Pageable pageable);
    boolean delete(int repoId);
    Map<String, Object> findAllUnassignedRepo();
    Respository findById(int repoId);
    List<Respository> findAll();
}
