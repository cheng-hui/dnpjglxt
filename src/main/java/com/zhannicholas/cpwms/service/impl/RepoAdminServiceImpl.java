package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.RepoAdmin;
import com.zhannicholas.cpwms.domain.repository.RepoAdminRepository;
import com.zhannicholas.cpwms.service.RepoAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RepoAdminServiceImpl implements RepoAdminService {
    private final RepoAdminRepository repoAdminRepository;

    @Autowired
    public RepoAdminServiceImpl(RepoAdminRepository repoAdminRepository) {
        this.repoAdminRepository = repoAdminRepository;
    }

    @Override
    public List<RepoAdmin> findAll() {
        return repoAdminRepository.findAll();
    }

    @Override
    public void save(RepoAdmin repoAdmin) {
        repoAdminRepository.save(repoAdmin);
    }

    @Override
    public RepoAdmin findOne(int repoAdminId) {
        return repoAdminRepository.findByRepoAdminId(repoAdminId);
    }

    @Override
    public RepoAdmin findByRepoAdminName(String repoAdminname) {
        return repoAdminRepository.findByRepoAdminName(repoAdminname);
    }

    @Override
    public void delete(int repoAdminId) {
        repoAdminRepository.deleteById(repoAdminId);
    }
}
