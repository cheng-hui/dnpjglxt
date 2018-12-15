package com.zhannicholas.cpwms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RecordStoragePK implements Serializable {
    private int recordPartsid;
    private int recordRepository;

    @Column(name = "RECORD_PARTSID", nullable = false)
    @Id
    public int getRecordPartsid() {
        return recordPartsid;
    }

    public void setRecordPartsid(int recordPartsid) {
        this.recordPartsid = recordPartsid;
    }

    @Column(name = "RECORD_REPOSITORY", nullable = false)
    @Id
    public int getRecordRepository() {
        return recordRepository;
    }

    public void setRecordRepository(int recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordStoragePK that = (RecordStoragePK) o;
        return recordPartsid == that.recordPartsid &&
                recordRepository == that.recordRepository;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordPartsid, recordRepository);
    }
}
