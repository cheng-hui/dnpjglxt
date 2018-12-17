package com.zhannicholas.cpwms.service;

import com.zhannicholas.cpwms.web.vo.RepoVo;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RespositoryService {
    Map<String, Object> findAll(Pageable pageable);
    boolean save(RepoVo repoVo);
    Map<String, Object> findOne(int repoId);
    Map<String, Object> findByRepoAddressContaining(String repoAddress, Pageable pageable);
    boolean delete(int repoId);
}
