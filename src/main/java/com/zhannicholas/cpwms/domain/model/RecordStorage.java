package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cpims_record_storage", schema = "dbo", catalog = "cpims_db")
@IdClass(RecordStoragePK.class)
public class RecordStorage {
    private int recordPartsid;
    private int recordRepository;
    private int recordNumber;
    private Parts parts;

    @Id
    @Column(name = "RECORD_PARTSID", nullable = false)
    public int getRecordPartsid() {
        return recordPartsid;
    }

    public void setRecordPartsid(int recordPartsid) {
        this.recordPartsid = recordPartsid;
    }

    @Id
    @Column(name = "RECORD_REPOSITORY", nullable = false)
    public int getRecordRepository() {
        return recordRepository;
    }

    public void setRecordRepository(int recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Basic
    @Column(name = "RECORD_NUMBER", nullable = false)
    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordStorage that = (RecordStorage) o;
        return recordPartsid == that.recordPartsid &&
                recordRepository == that.recordRepository &&
                recordNumber == that.recordNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordPartsid, recordRepository, recordNumber);
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_PARTSID", referencedColumnName = "PARTS_ID", nullable = false, insertable = false, updatable = false)
    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }
}
