package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Storage;
import com.zhannicholas.cpwms.domain.model.StoragePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StorageRepository extends JpaRepository<Storage, StoragePK> {
    /**
     * 查询指定配件在指定仓库的库存
     * @param partsId 配件id
     * @param repoId    仓库id
     * @return  某个配件在指定仓库的库存量
     */
    @Query(value = "SELECT RECORD_NUMBER FROM cpims_record_storage WHERE RECORD_PARTSID = ?1 AND RECORD_REPOSITORY = ?2", nativeQuery = true)
    Integer findNumberById(int partsId, int repoId);

    Storage findByPartsIdAndRepoId(int partsId, int repoId);

    @Modifying
    @Query(value = "UPDATE Storage s SET s.number = s.number + ?3 WHERE s.partsId = ?1 AND s.repoId = ?2")
    void updateStorage(int parsId, int repoId, int number);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cpims_record_storage(RECORD_PARTSID, RECORD_REPOSITORY, RECORD_NUMBER) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void saveStorage(int partsId, int repoId, int number);


}
