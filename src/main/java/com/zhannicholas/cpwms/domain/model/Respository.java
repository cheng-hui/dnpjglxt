package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cpims_respository", schema = "dbo", catalog = "cpims_db")
public class Respository {
    private int repoId;
    private String repoAddress;
    private String repoStatus;
    private String repoArea;
    private String repoDesc;

    @Id
    @Column(name = "REPO_ID", nullable = false)
    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    @Basic
    @Column(name = "REPO_ADDRESS", nullable = false, length = 30)
    public String getRepoAddress() {
        return repoAddress;
    }

    public void setRepoAddress(String repoAddress) {
        this.repoAddress = repoAddress;
    }

    @Basic
    @Column(name = "REPO_STATUS", nullable = false, length = 20)
    public String getRepoStatus() {
        return repoStatus;
    }

    public void setRepoStatus(String repoStatus) {
        this.repoStatus = repoStatus;
    }

    @Basic
    @Column(name = "REPO_AREA", nullable = false, length = 20)
    public String getRepoArea() {
        return repoArea;
    }

    public void setRepoArea(String repoArea) {
        this.repoArea = repoArea;
    }

    @Basic
    @Column(name = "REPO_DESC", nullable = true, length = 50)
    public String getRepoDesc() {
        return repoDesc;
    }

    public void setRepoDesc(String repoDesc) {
        this.repoDesc = repoDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respository that = (Respository) o;
        return repoId == that.repoId &&
                Objects.equals(repoAddress, that.repoAddress) &&
                Objects.equals(repoStatus, that.repoStatus) &&
                Objects.equals(repoArea, that.repoArea) &&
                Objects.equals(repoDesc, that.repoDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repoId, repoAddress, repoStatus, repoArea, repoDesc);
    }
}
