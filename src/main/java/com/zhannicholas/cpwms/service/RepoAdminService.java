package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.RepoAdmin;

import java.util.List;

public interface RepoAdminService {
    List<RepoAdmin> findAll();
    void save(RepoAdmin repoAdmin);
    RepoAdmin findOne(int repoAdminId);
    RepoAdmin findByRepoAdminName(String repoAdminname);
    void delete(int repoAdminId);
}
