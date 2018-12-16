package com.zhannicholas.cpwms.service.impl;

import com.zhannicholas.cpwms.domain.model.Parts;
import com.zhannicholas.cpwms.domain.repository.PartsRepository;
import com.zhannicholas.cpwms.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartsServiceImpl implements PartsService {
    private final PartsRepository partsRepository;

    @Autowired
    public PartsServiceImpl(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;
    }

    @Override
    public List<Parts> findAll() {
        return partsRepository.findAll();
    }

    @Override
    public void save(Parts parts) {
        partsRepository.save(parts);
    }

    @Override
    public Parts findOne(int partsId) {
        return partsRepository.findByPartsId(partsId);
    }

    @Override
    public List<Parts> findByPartsName(String partsName) {
        return partsRepository.findAllByPartsName(partsName);
    }

    @Override
    public List<Parts> findByPartsNameContaining(String partsName) {
        return partsRepository.findByPartsNameContaining(partsName);
    }

    @Override
    public void delete(int partsId) {
        partsRepository.deleteById(partsId);
    }
}
