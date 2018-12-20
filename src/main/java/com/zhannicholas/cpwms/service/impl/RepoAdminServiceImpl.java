package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.RepoAdmin;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.repository.RepoAdminRepository;
import com.zhannicholas.cpwms.domain.repository.RepoRepository;
import com.zhannicholas.cpwms.service.RepoAdminService;
import com.zhannicholas.cpwms.util.ToMapUtil;
import com.zhannicholas.cpwms.domain.dto.RepoAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RepoAdminServiceImpl implements RepoAdminService {
    private final RepoAdminRepository repoAdminRepository;

    private final RepoRepository repoRepository;

    @Autowired
    public RepoAdminServiceImpl(RepoAdminRepository repoAdminRepository, RepoRepository repoRepository) {
        this.repoAdminRepository = repoAdminRepository;
        this.repoRepository = repoRepository;
    }


    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        Page<RepoAdmin> repoAdminPage = repoAdminRepository.findAll(pageable);
        return page2Map(repoAdminPage);
    }

    @Override
    public boolean save(RepoAdmin repoAdmin) {
        if(isValidRepoAdmin(repoAdmin)){
            repoAdminRepository.save(repoAdmin);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findByRepoAdminId(int repoAdminId) {
        RepoAdmin repoAdmin = repoAdminRepository.findByRepoAdminId(repoAdminId);
        return ToMapUtil.fromAInstance(repoAdmin2RepoAdminDTO(repoAdmin));
    }

    @Override
    public Map<String, Object> findByRepoId(int repoId) {
        Respository respository = repoRepository.findByRepoId(repoId);
        RepoAdmin repoAdmin = repoAdminRepository.findByRepository(respository);
        return ToMapUtil.fromAInstance(repoAdmin2RepoAdminDTO(repoAdmin));
    }

    @Override
    public Map<String, Object> findByRepoAdminNameContaining(String repoAdminname, Pageable pageable) {
        Page<RepoAdmin> repoAdminPage = repoAdminRepository.findByRepoAdminNameContaining(repoAdminname, pageable);
        return page2Map(repoAdminPage);
    }

    @Override
    public boolean delete(int repoAdminId) {
        if(isValidRepoAdminId(repoAdminId)){
            repoAdminRepository.deleteById(repoAdminId);
            return true;
        }
        return false;
    }

    /**
     * 检查仓库管理员信息是否满足要求
     * @param repoAdmin 仓库管理员
     * @return  若 repoAdmin 合法则返回true,否则返回false
     */
    private boolean isValidRepoAdmin(RepoAdmin repoAdmin){
        if(repoAdmin != null){
            return repoAdmin.getRepoAdminName() != null &&
                    repoAdmin.getRepoAdminSex() != null &&
                    repoAdmin.getRepoAdminTel() != null &&
                    repoAdmin.getRepoAdminAddress() != null &&
                    repoAdmin.getRepoAdminBirth() != null;
        }
        return false;
    }

    /**
     * 检查 repoAdminId 是否合法
     * @param repoAdminId   仓库管理员Id
     * @return  若仓库管理员id合法则返回true,否则返回false
     */
    private boolean isValidRepoAdminId(int repoAdminId){
        return repoAdminRepository.findByRepoAdminId(repoAdminId) != null;
    }

    /**
     * 将 repoAdmin 对象转为 repoAdminVo 对象
     * @param repoAdmin 仓库管理员对象
     * @return  仓库管理员对应的视图对象
     */
    private RepoAdminDTO repoAdmin2RepoAdminDTO(RepoAdmin repoAdmin){
        RepoAdminDTO repoAdminDTO = new RepoAdminDTO();
        repoAdminDTO.setRepoAdminId(repoAdmin.getRepoAdminId());
        repoAdminDTO.setRepoAdminName(repoAdmin.getRepoAdminName());
        repoAdminDTO.setRepoAdminSex(repoAdmin.getRepoAdminSex());
        repoAdminDTO.setRepoAdminTel(repoAdmin.getRepoAdminTel());
        repoAdminDTO.setRepoAdminAddress(repoAdmin.getRepoAdminAddress());
        repoAdminDTO.setRepoAdminBirth(repoAdmin.getRepoAdminBirth());
        Respository respository = repoAdmin.getRepository();
        if(respository != null){
            repoAdminDTO.setRepoId(respository.getRepoId());
        }
        return repoAdminDTO;
    }

    private Map<String, Object> page2Map(Page<RepoAdmin> repoAdminPage){
        List<RepoAdmin> repoAdminList = repoAdminPage.getContent();
        List<RepoAdminDTO> repoAdminDTOList = new ArrayList<>();
        for(RepoAdmin repoAdmin: repoAdminList){
            repoAdminDTOList.add(repoAdmin2RepoAdminDTO(repoAdmin));
        }

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", repoAdminDTOList);
        resultSet.put("total", repoAdminDTOList.size());
        return resultSet;
    }
}
