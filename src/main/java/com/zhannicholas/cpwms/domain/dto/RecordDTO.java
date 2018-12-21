package com.zhannicholas.cpwms.domain.dto;

import com.zhannicholas.cpwms.util.DateUtil;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

//入出库记录
public class RecordDTO implements Serializable {
    private int id;
    private String supplierOrCustomerName;
    private String partsName;
    private int repoId;
    private int number;
    private Date time;
    private String person;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierOrCustomerName() {
        return supplierOrCustomerName;
    }

    public void setSupplierOrCustomerName(String supplierOrCustomerName) {
        this.supplierOrCustomerName = supplierOrCustomerName;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RecordDTO(int id, String supplierOrCustomerName, String partsName, int repoId, int number, java.util.Date time, String person, String type) {
        this.id = id;
        this.supplierOrCustomerName = supplierOrCustomerName;
        this.partsName = partsName;
        this.repoId = repoId;
        this.number = number;
        this.time = DateUtil.fromUtilDate(time);
        this.person = person;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordDTO recordDTO = (RecordDTO) o;
        return getId() == recordDTO.getId() &&
                getRepoId() == recordDTO.getRepoId() &&
                getNumber() == recordDTO.getNumber() &&
                Objects.equals(getSupplierOrCustomerName(), recordDTO.getSupplierOrCustomerName()) &&
                Objects.equals(getPartsName(), recordDTO.getPartsName()) &&
                Objects.equals(getTime(), recordDTO.getTime()) &&
                Objects.equals(getPerson(), recordDTO.getPerson()) &&
                Objects.equals(getType(), recordDTO.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSupplierOrCustomerName(), getPartsName(), getRepoId(), getNumber(), getTime(), getPerson(), getType());
    }

    @Override
    public String toString() {
        return "RecordDTO{" +
                "id=" + id +
                ", supplierOrCustomerName='" + supplierOrCustomerName + '\'' +
                ", partsName='" + partsName + '\'' +
                ", repoId=" + repoId +
                ", number=" + number +
                ", time=" + time +
                ", person='" + person + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
