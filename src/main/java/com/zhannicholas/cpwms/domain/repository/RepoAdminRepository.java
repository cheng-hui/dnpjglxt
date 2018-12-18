package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.RepoAdmin;
import com.zhannicholas.cpwms.domain.model.Respository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepoAdminRepository extends JpaRepository<RepoAdmin, Integer> {
    /**
     * 根据 repoAdminId 查找仓库管理员
     * @param repoAdminId   仓库管理员id
     * @return  和 repoAdminId 对应的仓库管理员
     */
    RepoAdmin findByRepoAdminId(int repoAdminId);

    /**
     * 根据 repository 查找仓库管理员
     * @param respository   仓库
     * @return  和 respository 对应的仓库管理员
     */
    RepoAdmin findByRepository(Respository respository);

    /**
     * 根据 repoAdminName 查找仓库管理员---模糊查询
     * @param repoAdminName 仓库管理员姓名
     * @param pageable 分页参数
     * @return  与 repoAdminName 和 pageable 对应的仓库管理员
     */
    Page<RepoAdmin> findByRepoAdminNameContaining(String repoAdminName, Pageable pageable);
}
