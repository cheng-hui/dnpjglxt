package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cpims_record_storage", schema = "dbo", catalog = "cpims_db")
@IdClass(StoragePK.class)
public class Storage {
    private int partsId;
    private int repoId;
    private int number;

    @Id
    @Column(name = "RECORD_PARTSID", nullable = false)
    public int getPartsId() {
        return partsId;
    }

    public void setPartsId(int recordPartsId) {
        this.partsId = recordPartsId;
    }

    @Id
    @Column(name = "RECORD_REPOSITORY", nullable = false)
    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int recordRepository) {
        this.repoId = recordRepository;
    }

    @Basic
    @Column(name = "RECORD_NUMBER", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int recordNumber) {
        this.number = recordNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return partsId == storage.partsId &&
                repoId == storage.repoId &&
                number == storage.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(partsId, repoId, number);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "recordPartsid=" + partsId +
                ", recordRepository=" + repoId +
                ", recordNumber=" + number +
                '}';
    }
}
