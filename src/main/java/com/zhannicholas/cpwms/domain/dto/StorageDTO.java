package com.zhannicholas.cpwms.domain.dto;


import java.io.Serializable;

// 库存
public class StorageDTO implements Serializable {
    private Integer partsId;
    private String partsName;
    private String partsType;
    private Integer repoId;
    private Integer number;

    public StorageDTO(){}

    public StorageDTO(Integer partsId, String partsName, String partsType, Integer repoId, Integer number) {
        this.partsId = partsId;
        this.partsName = partsName;
        this.partsType = partsType;
        this.repoId = repoId;
        this.number = number;
    }

    public Integer getPartsId() {
        return partsId;
    }

    public void setPartsId(Integer partsId) {
        this.partsId = partsId;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPartsType() {
        return partsType;
    }

    public void setPartsType(String partsType) {
        this.partsType = partsType;
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "StorageDTO{" +
                "partsId=" + partsId +
                ", partsName='" + partsName + '\'' +
                ", partsType='" + partsType + '\'' +
                ", repoId=" + repoId +
                ", number=" + number +
                '}';
    }
}
