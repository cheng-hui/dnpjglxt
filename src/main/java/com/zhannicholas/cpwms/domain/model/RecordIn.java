package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cpims_record_in", schema = "dbo", catalog = "cpims_db")
public class RecordIn {
    private int recordId;
    private int recordNumber;
    private Date recordTime;
    private String recordPerson;
    private Supplier supplier;
    private Parts parts;
    private Respository repository;

    @Id
    @Column(name = "RECORD_ID", nullable = false)
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "RECORD_NUMBER", nullable = false)
    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    @Basic
    @Column(name = "RECORD_TIME", nullable = false)
    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Basic
    @Column(name = "RECORD_PERSON", nullable = false, length = 10)
    public String getRecordPerson() {
        return recordPerson;
    }

    public void setRecordPerson(String recordPerson) {
        this.recordPerson = recordPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordIn recordIn = (RecordIn) o;
        return recordId == recordIn.recordId &&
                recordNumber == recordIn.recordNumber &&
                Objects.equals(recordTime, recordIn.recordTime) &&
                Objects.equals(recordPerson, recordIn.recordPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, recordNumber, recordTime, recordPerson);
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_SUPPLIERID", referencedColumnName = "SUPPLIER_ID", nullable = false)
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_PARTSID", referencedColumnName = "PARTS_ID", nullable = false)
    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_REPOSITORYID", referencedColumnName = "REPO_ID", nullable = false)
    public Respository getRepository() {
        return repository;
    }

    public void setRepository(Respository repository) {
        this.repository = repository;
    }
}
