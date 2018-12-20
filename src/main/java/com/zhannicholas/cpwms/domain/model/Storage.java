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
    private Parts parts;
    private Respository respository;

    @Id
    @Column(name = "RECORD_PARTSID", nullable = false)
    public int getPartsId() {
        return partsId;
    }

    public void setPartsId(int recordPartsid) {
        this.partsId = recordPartsid;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_PARTSID", referencedColumnName = "PARTS_ID", nullable = false, insertable = false, updatable = false)
    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_REPOSITORY", referencedColumnName = "REPO_ID", nullable = false, insertable = false, updatable = false)
    public Respository getRespository() {
        return respository;
    }

    public void setRespository(Respository respository) {
        this.respository = respository;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "partsId=" + partsId +
                ", repoId=" + repoId +
                ", number=" + number +
                '}';
    }
}
