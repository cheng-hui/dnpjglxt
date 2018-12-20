package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.dto.RepoDTO;
import com.zhannicholas.cpwms.domain.model.RepoAdmin;
import com.zhannicholas.cpwms.domain.model.Respository;
import com.zhannicholas.cpwms.domain.repository.RepoRepository;
import com.zhannicholas.cpwms.service.RespositoryService;
import com.zhannicholas.cpwms.util.ToMapUtil;
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
public class RespositoryServiceImpl implements RespositoryService {
    private final RepoRepository repoRepository;

    @Autowired
    public RespositoryServiceImpl(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }


    @Override
    public Map<String, Object> findAll(Pageable pageable) {
        List<Respository> respositoryList = null;
        if(pageable == null){
            respositoryList = repoRepository.findAll();
            System.out.println(respositoryList);
        }
        else{
            Page<Respository> repoPage = repoRepository.findAll(pageable);
            respositoryList = repoPage.getContent();
        }
        List<RepoDTO> repoDTOList = new ArrayList<>();
        for(Respository respository: respositoryList){
            repoDTOList.add(repo2RepoDTO(respository));
        }

        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        resultSet.put("rows", repoDTOList);
        resultSet.put("total", repoDTOList.size());
        return resultSet;
    }

    @Override
    public boolean save(Respository respository) {
        if(isValidRepo(respository)){
            repoRepository.save(respository);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findOne(int repoId) {
        Respository respository = repoRepository.findByRepoId(repoId);
        return ToMapUtil.fromAInstance(respository);
    }

    @Override
    public Map<String, Object> findByRepoAddressContaining(String repoAddress, Pageable pageable) {
        Page<Respository> respositoryPage = repoRepository.findByRepoAddressContaining(repoAddress, pageable);
        return ToMapUtil.fromAPage(respositoryPage);
    }

    @Override
    public boolean delete(int repoId) {
        if(isValidRepoId(repoId)){
            repoRepository.deleteById(repoId);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> findAllUnassignedRepo() {
        List<Respository> respositoryList = repoRepository.findUnassignedRepo();
        return ToMapUtil.fromAList(respositoryList);
    }

    @Override
    public Respository findById(int repoId) {
        return repoRepository.findByRepoId(repoId);
    }

    @Override
    public List<Respository> findAll() {
        return repoRepository.findAll();
    }

    /**
     * 检查仓库信息是否满足要求
     * @param repo  仓库
     * @return  若仓库信息满足要求则返回true，否则返回false
     */
    public boolean isValidRepo(Respository repo){
        if(repo != null){
            return repo.getRepoStatus() != null &&
                    repo.getRepoArea() != null &&
                    repo.getRepoAddress() != null;
        }
        return false;
    }

    /**
     * 检查仓库视图信息是否满足要求
     * @param repoDTO  仓库
     * @return  若仓库视图信息满足要求则返回true，否则返回false
     */
    private boolean isValidRepoDTO(RepoDTO repoDTO){
        if(repoDTO != null){
            return repoDTO.getRepoAddress() != null &&
                    repoDTO.getRepoStatus() != null &&
                    repoDTO.getRepoArea() != null;
        }
        return false;
    }


    /**
     * 检查 repoId 是否合法
     * @param repoId    仓库id
     * @return  若仓库id合法则返回true,否则返回false
     */
    public boolean isValidRepoId(int repoId){
        return repoRepository.findByRepoId(repoId) != null;
    }

    /**
     * 将 repo 对象转为视图对象
     * @param repo  仓库对象
     * @return  repo 对应的视图对象
     */
    private RepoDTO repo2RepoDTO(Respository repo){
        RepoDTO repoDTO = new RepoDTO();
        repoDTO.setRepoId(repo.getRepoId());
        repoDTO.setRepoAddress(repo.getRepoAddress());
        repoDTO.setRepoArea(repo.getRepoArea());
        repoDTO.setRepoStatus(repo.getRepoStatus());
        repoDTO.setRepoDesc(repo.getRepoDesc());
        RepoAdmin repoAdmin = repo.getRepoAdmin();
        repoDTO.setRepoAdminName(repoAdmin != null ? repo.getRepoAdmin().getRepoAdminName() : null);
        return repoDTO;
    }

    /**
     * 将 repoDTO 转为实体对象
     * @param repoDTO    仓库视图对象
     * @return  repoDTO 对应的实体对象
     */
    private Respository repoDTO2Repo(RepoDTO repoDTO){
        Respository repo = new Respository();
        repo.setRepoId(repoDTO.getRepoId());
        repo.setRepoAddress(repoDTO.getRepoAddress());
        repo.setRepoArea(repoDTO.getRepoArea());
        repo.setRepoStatus(repoDTO.getRepoStatus());
        repo.setRepoDesc(repoDTO.getRepoDesc());
        return repo;
    }
}
