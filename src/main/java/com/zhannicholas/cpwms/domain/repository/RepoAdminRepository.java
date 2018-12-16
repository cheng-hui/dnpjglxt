package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.RepoAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoAdminRepository extends JpaRepository<RepoAdmin, Integer> {
    /**
     * 根据 repoAdminId 查找仓库管理员
     * @param repoAdminId   仓库管理员id
     * @return  和 repoAdminId 对应的仓库管理员
     */
    RepoAdmin findByRepoAdminId(int repoAdminId);

    /**
     * 根据 repoAdminName 查找仓库管理员
     * @param repoAdminName 仓库管理员姓名
     * @return  和 repoAdminName 对应的仓库管理员
     */
    RepoAdmin findByRepoAdminName(String repoAdminName);
}
