package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.Parts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartsRepository extends JpaRepository<Parts, Integer> {

    Page<Parts> findAll(Pageable pageable);

    /**
     * 根据 partsId 查找电脑配件
     * @param partsId   电脑配件id
     * @return  和 partsId 对应的电脑配件
     */
    Parts findByPartsId(int partsId);

    /**
     * 根据 partsName 查找电脑配件---精确查询
     * @param partsName 电脑配件名
     * @return  和 partsName 匹配的所有电脑配件
     */
    List<Parts> findAllByPartsName(String partsName);

    /**
     * 根据 partsName 查找电脑配件---模糊查询
     * @param partsName 电脑配件名
     * @return  和 partsName 匹配的所有电脑配件
     */
    List<Parts> findByPartsNameContaining(String partsName);
}
