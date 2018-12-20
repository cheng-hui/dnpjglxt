package com.zhannicholas.cpwms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StoragePK implements Serializable {
    private int recordPartsid;
    private int recordRepository;

    @Column(name = "RECORD_PARTSID", nullable = false)
    @Id
    public int getPartsId() {
        return recordPartsid;
    }

    public void setPartsId(int recordPartsid) {
        this.recordPartsid = recordPartsid;
    }

    @Column(name = "RECORD_REPOSITORY", nullable = false)
    @Id
    public int getRepoId() {
        return recordRepository;
    }

    public void setRepoId(int recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoragePK storagePK = (StoragePK) o;
        return recordPartsid == storagePK.recordPartsid &&
                recordRepository == storagePK.recordRepository;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordPartsid, recordRepository);
    }
}
