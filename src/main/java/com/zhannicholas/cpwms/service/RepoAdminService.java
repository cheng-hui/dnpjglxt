package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.domain.model.RepoAdmin;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RepoAdminService {
    Map<String, Object> findAll(Pageable pageable);
    boolean save(RepoAdmin repoAdmin);
    Map<String, Object> findByRepoAdminId(int repoAdminId);
    Map<String, Object> findByRepoId(int repoId);
    Map<String, Object> findByRepoAdminNameContaining(String repoAdminname, Pageable pageable);
    boolean delete(int repoAdminId);
}
