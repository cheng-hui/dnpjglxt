package com.zhannicholas.cpwms.domain.dto;

// 仓库视图对象
public class RepoDTO {
    private int repoId;
    private String repoAddress;
    private String repoStatus;
    private String repoArea;
    private String repoDesc;
    private String repoAdminName;   // 仓库管理员

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    public String getRepoAddress() {
        return repoAddress;
    }

    public void setRepoAddress(String repoAddress) {
        this.repoAddress = repoAddress;
    }

    public String getRepoStatus() {
        return repoStatus;
    }

    public void setRepoStatus(String repoStatus) {
        this.repoStatus = repoStatus;
    }

    public String getRepoArea() {
        return repoArea;
    }

    public void setRepoArea(String repoArea) {
        this.repoArea = repoArea;
    }

    public String getRepoDesc() {
        return repoDesc;
    }

    public void setRepoDesc(String repoDesc) {
        this.repoDesc = repoDesc;
    }

    public String getRepoAdminName() {
        return repoAdminName;
    }

    public void setRepoAdminName(String repoAdminName) {
        this.repoAdminName = repoAdminName;
    }

    @Override
    public String toString() {
        return "RepoDTO{" +
                "repoId=" + repoId +
                ", repoAddress='" + repoAddress + '\'' +
                ", repoStatus='" + repoStatus + '\'' +
                ", repoArea='" + repoArea + '\'' +
                ", repoDesc='" + repoDesc + '\'' +
                ", repoAdminName='" + repoAdminName + '\'' +
                '}';
    }
}
