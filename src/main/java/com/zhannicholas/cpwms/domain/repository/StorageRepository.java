package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.dto.StorageDTO;
import com.zhannicholas.cpwms.domain.model.Storage;
import com.zhannicholas.cpwms.domain.model.StoragePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, StoragePK> {
    /**
     * 查询指定配件在指定仓库的库存
     * @param partsId 配件id
     * @param repoId    仓库id
     * @return  某个配件在指定仓库的库存量
     */
    @Query(value = "SELECT RECORD_NUMBER FROM cpims_record_storage WHERE RECORD_PARTSID = ?1 AND RECORD_REPOSITORY = ?2", nativeQuery = true)
    Integer findNumberById(int partsId, int repoId);

    /**
     * 更新库存
     * @param parsId    配件Id
     * @param repoId    仓库Id
     * @param number    增加或减少的配件数量
     */
    @Modifying
    @Query(value = "UPDATE Storage s SET s.number = s.number + ?3 WHERE s.partsId = ?1 AND s.repoId = ?2")
    void changeStorage(int parsId, int repoId, int number);

    /**
     * 向库存表插入一条数据
     * @param partsId   配件Id
     * @param repoId    仓库Id
     * @param number    配件数量
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cpims_record_storage(RECORD_PARTSID, RECORD_REPOSITORY, RECORD_NUMBER) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void saveStorage(int partsId, int repoId, int number);


    @Modifying
    @Transactional
    @Query(value = "UPDATE cpims_record_storage SET RECORD_NUMBER = ?3 WHERE RECORD_PARTSID = ?1 AND RECORD_REPOSITORY = ?2", nativeQuery = true)
    void updateStorage(int partsId, int repoId, int number);

    /**
     * 查询所有库存记录---分页查询
     * @param pageable  分页参数
     * @return  和 pageable 对应的库存记录
     */
    //Page<Storage> findAll(Pageable pageable);

    /**
     * 查询某一条库存记录
     * @param partsId   配件Id
     * @param repoId    仓库Id
     * @return  一条特定的库存记录
     */
    Storage findByPartsIdAndRepoId(int partsId, int repoId);

    /**
     * 查询 StorageDTO 对象
     * @param partsId   配件Id
     * @param repoId    仓库Id
     * @return  相应的 StorageDTO 对象
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where s.repoId = r.repoId and s.partsId = p.partsId and p.partsId = ?1 and r.repoId = ?2")
    StorageDTO findStorageDTO(int partsId, int repoId);

    /**
     * 查询所有 StorageDTO 对象---不区分仓库
     * @param pageable  分页参数
     * @return  相应的 StorageDTO 对象页
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where s.repoId = r.repoId and s.partsId = p.partsId")
    Page<StorageDTO> findAllStorageDTO(Pageable pageable);

    /**
     * 查询某个仓库下所有的配件
     * @param repoId    仓库Id
     * @param pageable  分页参数
     * @return  符合要求的 StorageDTO 页
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where s.repoId = r.repoId and r.repoId = ?1 and s.partsId = p.partsId")
    Page<StorageDTO> findAllStorageDTO(int repoId, Pageable pageable);

    /**
     * 查询 partsId 对应的所有 StorageDTO 对象----不分区仓库
     * @param partsId   配件Id
     * @param pageable  分页参数
     * @return  和 partsId 对应的所有 StorageDTO 对象
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where p.partsId = ?1 and r.repoId = s.repoId and s.partsId = p.partsId")
    Page<StorageDTO> findStorageDTO(int partsId, Pageable pageable);

    /**
     * 按配件名查找---区分仓库
     * @param repoId    仓库Id
     * @param partsName 配件名
     * @param pageable  分页参数
     * @return  符合要求的 StorageDTO 页
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where s.repoId = r.repoId and r.repoId = ?1 and p.partsName like %?2% and p.partsId = s.partsId")
    Page<StorageDTO> findUsingPartsName(int repoId, String partsName, Pageable pageable);

    /**
     * 按配件名查找---不区分仓库
     * @param partsName 配件名
     * @param pageable  分页参数
     * @return  符合要求的 StorageDTO 页
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where s.repoId = r.repoId and p.partsName like %?1% and p.partsId = s.partsId")
    Page<StorageDTO> findUsingPartsName(String partsName, Pageable pageable);

    /**
     * 按配件类型查找---区分仓库
     * @param repoId    仓库Id
     * @param partsType 配件类型
     * @param pageable  分页参数
     * @return  符合要求的 StorageDTO 页
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where s.repoId = r.repoId and r.repoId = ?1 and p.partsType like %?2% and p.partsId = s.partsId")
    Page<StorageDTO> findUsingPartsType(int repoId, String partsType, Pageable pageable);

    /**
     * 按配件类型查找----不区分仓库
     * @param partsType 配件类型
     * @param pageable  分页参数
     * @return  符合要求的 StorageDTO 页
     */
    @Query("select new com.zhannicholas.cpwms.domain.dto.StorageDTO(p.partsId, p.partsName, p.partsType, r.repoId, s.number) " +
            "from Storage s, Parts p, Respository r where s.repoId = r.repoId and p.partsType like %?1% and p.partsId = s.partsId")
    Page<StorageDTO> findUsingPartsType(String partsType, Pageable pageable);


    // 删除库存信息
    @Modifying
    @Query(value = "DELETE FROM cpims_record_storage WHERE RECORD_PARTSID = ?1 AND RECORD_REPOSITORY = ?2", nativeQuery = true)
    void delete(int partsId, int repoId);
}
