package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.Respository;

import java.util.List;

public interface RespositoryService {
    List<Respository> findAll();
    void save(Respository respository);
    Respository findOne(int repoId);
    List<Respository> findByRepoAddress(String repoAddress);
    void delete(int repoId);
}
