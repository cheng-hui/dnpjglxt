package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cpims_record_storage", schema = "dbo", catalog = "cpims_db")
public class Storage implements Serializable {
    private int recordNumber;
    private Parts parts;
    private Respository respository;


    @Basic
    @Column(name = "RECORD_NUMBER", nullable = false)
    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }


    @Id
    @ManyToOne
    @JoinColumn(name = "RECORD_PARTSID", referencedColumnName = "PARTS_ID", nullable = false, insertable = false, updatable = false)
    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "RECORD_REPOSITORY", referencedColumnName = "REPO_ID", nullable = false, insertable = false, updatable = false)
    public Respository getRespository() {
        return respository;
    }

    public void setRespository(Respository respository) {
        this.respository = respository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return getRecordNumber() == storage.getRecordNumber() &&
                Objects.equals(getParts(), storage.getParts()) &&
                Objects.equals(getRespository(), storage.getRespository());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecordNumber(), getParts(), getRespository());
    }
}
