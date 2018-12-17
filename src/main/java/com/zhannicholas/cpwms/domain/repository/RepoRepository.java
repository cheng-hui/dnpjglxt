package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Respository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoRepository extends JpaRepository<Respository, Integer> {
    /**
     * 根据 repoId 查找 repository
     * @param repoId    仓库id
     * @return  具有 repoId 的仓库
     */
    Respository findByRepoId(int repoId);

    /**
     * 根据 repoAddress 查询仓库---模糊查询
     * @param repoAddress   仓库地址
     * @param pageable 分页参数
     * @return  具有 repoAddress 子串的地址对应的所有仓库
     */
    Page<Respository> findByRepoAddressContaining(String repoAddress, Pageable pageable);

    /**
     * 查找所有仓库
     * @param pageable  分页参数
     * @return  和 pageable 对应的所有仓库
     */
    Page<Respository> findAll(Pageable pageable);
}
