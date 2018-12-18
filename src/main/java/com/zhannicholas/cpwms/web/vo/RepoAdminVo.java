package com.zhannicholas.cpwms.web.vo;

import java.sql.Date;

// 仓库管理员视图对象
public class RepoAdminVo {
    private int repoAdminId;
    private String repoAdminName;
    private String repoAdminSex;
    private String repoAdminTel;
    private String repoAdminAddress;
    private Date repoAdminBirth;
    private Integer repoId;

    public int getRepoAdminId() {
        return repoAdminId;
    }

    public void setRepoAdminId(int repoAdminId) {
        this.repoAdminId = repoAdminId;
    }

    public String getRepoAdminName() {
        return repoAdminName;
    }

    public void setRepoAdminName(String repoAdminName) {
        this.repoAdminName = repoAdminName;
    }

    public String getRepoAdminSex() {
        return repoAdminSex;
    }

    public void setRepoAdminSex(String repoAdminSex) {
        this.repoAdminSex = repoAdminSex;
    }

    public String getRepoAdminTel() {
        return repoAdminTel;
    }

    public void setRepoAdminTel(String repoAdminTel) {
        this.repoAdminTel = repoAdminTel;
    }

    public String getRepoAdminAddress() {
        return repoAdminAddress;
    }

    public void setRepoAdminAddress(String repoAdminAddress) {
        this.repoAdminAddress = repoAdminAddress;
    }

    public Date getRepoAdminBirth() {
        return repoAdminBirth;
    }

    public void setRepoAdminBirth(Date repoAdminBirth) {
        this.repoAdminBirth = repoAdminBirth;
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }
}
