package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cpims_repo_admin", schema = "dbo", catalog = "cpims_db")
public class RepoAdmin {
    private int repoAdminId;
    private String repoAdminName;
    private String repoAdminSex;
    private String repoAdminTel;
    private String repoAdminAddress;
    private Date repoAdminBirth;
    private Respository repository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPO_ADMIN_ID", nullable = false)
    public int getRepoAdminId() {
        return repoAdminId;
    }

    public void setRepoAdminId(int repoAdminId) {
        this.repoAdminId = repoAdminId;
    }

    @Basic
    @Column(name = "REPO_ADMIN_NAME", nullable = false, length = 10)
    public String getRepoAdminName() {
        return repoAdminName;
    }

    public void setRepoAdminName(String repoAdminName) {
        this.repoAdminName = repoAdminName;
    }

    @Basic
    @Column(name = "REPO_ADMIN_SEX", nullable = false, length = 10)
    public String getRepoAdminSex() {
        return repoAdminSex;
    }

    public void setRepoAdminSex(String repoAdminSex) {
        this.repoAdminSex = repoAdminSex;
    }

    @Basic
    @Column(name = "REPO_ADMIN_TEL", nullable = false, length = 20)
    public String getRepoAdminTel() {
        return repoAdminTel;
    }

    public void setRepoAdminTel(String repoAdminTel) {
        this.repoAdminTel = repoAdminTel;
    }

    @Basic
    @Column(name = "REPO_ADMIN_ADDRESS", nullable = false, length = 30)
    public String getRepoAdminAddress() {
        return repoAdminAddress;
    }

    public void setRepoAdminAddress(String repoAdminAddress) {
        this.repoAdminAddress = repoAdminAddress;
    }

    @Basic
    @Column(name = "REPO_ADMIN_BIRTH", nullable = false)
    public Date getRepoAdminBirth() {
        return repoAdminBirth;
    }

    public void setRepoAdminBirth(Date repoAdminBirth) {
        this.repoAdminBirth = repoAdminBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepoAdmin repoAdmin = (RepoAdmin) o;
        return repoAdminId == repoAdmin.repoAdminId &&
                Objects.equals(repoAdminName, repoAdmin.repoAdminName) &&
                Objects.equals(repoAdminSex, repoAdmin.repoAdminSex) &&
                Objects.equals(repoAdminTel, repoAdmin.repoAdminTel) &&
                Objects.equals(repoAdminAddress, repoAdmin.repoAdminAddress) &&
                Objects.equals(repoAdminBirth, repoAdmin.repoAdminBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repoAdminId, repoAdminName, repoAdminSex, repoAdminTel, repoAdminAddress, repoAdminBirth);
    }

    @OneToOne
    @JoinColumn(name = "REPO_ADMIN_REPOID", referencedColumnName = "REPO_ID", insertable = false, updatable = false)
    public Respository getRepository() {
        return repository;
    }

    public void setRepository(Respository repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "RepoAdmin{" +
                "repoAdminId=" + repoAdminId +
                ", repoAdminName='" + repoAdminName + '\'' +
                ", repoAdminSex='" + repoAdminSex + '\'' +
                ", repoAdminTel='" + repoAdminTel + '\'' +
                ", repoAdminAddress='" + repoAdminAddress + '\'' +
                ", repoAdminBirth=" + repoAdminBirth +
                /*", repository=" + repository +*/
                '}';
    }
}
