package com.zhannicholas.cpwms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cpims_parts", schema = "dbo", catalog = "cpims_db")
public class Parts {
    private int partsId;
    private String partsName;
    private String partsType;
    private String partsSize;
    private double partsValue;
    private List<RecordIn> recordInList;
    private List<RecordOut> recordOutList;
    private List<Storage> storageList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARTS_ID", nullable = false)
    public int getPartsId() {
        return partsId;
    }

    public void setPartsId(int partsId) {
        this.partsId = partsId;
    }

    @Basic
    @Column(name = "PARTS_NAME", nullable = false, length = 30)
    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    @Basic
    @Column(name = "PARTS_TYPE", nullable = true, length = 20)
    public String getPartsType() {
        return partsType;
    }

    public void setPartsType(String partsType) {
        this.partsType = partsType;
    }

    @Basic
    @Column(name = "PARTS_SIZE", nullable = true, length = 20)
    public String getPartsSize() {
        return partsSize;
    }

    public void setPartsSize(String partsSize) {
        this.partsSize = partsSize;
    }

    @Basic
    @Column(name = "PARTS_VALUE", nullable = false, precision = 0)
    public double getPartsValue() {
        return partsValue;
    }

    public void setPartsValue(double partsValue) {
        this.partsValue = partsValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parts parts = (Parts) o;
        return partsId == parts.partsId &&
                Double.compare(parts.partsValue, partsValue) == 0 &&
                Objects.equals(partsName, parts.partsName) &&
                Objects.equals(partsType, parts.partsType) &&
                Objects.equals(partsSize, parts.partsSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partsId, partsName, partsType, partsSize, partsValue);
    }

    @OneToMany(mappedBy = "parts", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<RecordIn> getRecordInList() {
        return recordInList;
    }

    public void setRecordInList(List<RecordIn> recordInList) {
        this.recordInList = recordInList;
    }

    @OneToMany(mappedBy = "parts", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<RecordOut> getRecordOutList() {
        return recordOutList;
    }

    public void setRecordOutList(List<RecordOut> recordOutList) {
        this.recordOutList = recordOutList;
    }

    @Override
    public String toString() {
        return "Parts{" +
                "partsId=" + partsId +
                ", partsName='" + partsName + '\'' +
                ", partsType='" + partsType + '\'' +
                ", partsSize='" + partsSize + '\'' +
                ", partsValue=" + partsValue +
                '}';
    }

    @OneToMany(mappedBy = "parts")
    @JsonIgnore
    public List<Storage> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<Storage> storageList) {
        this.storageList = storageList;
    }
}
