package com.zhannicholas.cpwms.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StoragePK implements Serializable {
    private int partsId;
    private int repoId;

    @Column(name = "RECORD_PARTSID", nullable = false)
    @Id
    public int getPartsId() {
        return partsId;
    }

    public void setPartsId(int partsId) {
        this.partsId = partsId;
    }

    @Column(name = "RECORD_REPOSITORY", nullable = false)
    @Id
    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoragePK storagePK = (StoragePK) o;
        return partsId == storagePK.partsId &&
                repoId == storagePK.repoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(partsId, repoId);
    }
}
