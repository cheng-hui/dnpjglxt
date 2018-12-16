package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cpims_record_out", schema = "dbo", catalog = "cpims_db")
public class RecordOut {
    private int recordId;
    private int recordNumber;
    private Date recordTime;
    private String recordPerson;
    private Customer customer;
    private Parts parts;
    private Respository repository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        RecordOut recordOut = (RecordOut) o;
        return recordId == recordOut.recordId &&
                recordNumber == recordOut.recordNumber &&
                Objects.equals(recordTime, recordOut.recordTime) &&
                Objects.equals(recordPerson, recordOut.recordPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, recordNumber, recordTime, recordPerson);
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_CUSTOMERID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public String toString() {
        return "RecordOut{" +
                "recordId=" + recordId +
                ", recordNumber=" + recordNumber +
                ", recordTime=" + recordTime +
                ", recordPerson='" + recordPerson + '\'' +
                ", customer=" + customer +
                ", parts=" + parts +
                ", repository=" + repository +
                '}';
    }
}
