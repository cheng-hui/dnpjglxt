package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.repository.RepoRepository;
import com.zhannicholas.cpwms.service.RespositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RespositoryServiceImpl implements RespositoryService {
    private final RepoRepository repoRepository;

    @Autowired
    public RespositoryServiceImpl(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    @Override
    public List<Respository> findAll() {
        return repoRepository.findAll();
    }

    @Override
    public void save(Respository respository) {
        repoRepository.save(respository);
    }

    @Override
    public Respository findOne(int repoId) {
        return repoRepository.findByRepoId(repoId);
    }

    @Override
    public List<Respository> findByRepoAddress(String repoAddress) {
        return repoRepository.findByRepoAddressContaining(repoAddress);
    }

    @Override
    public void delete(int repoId) {
        repoRepository.deleteById(repoId);
    }
}
