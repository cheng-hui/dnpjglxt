package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cpims_user", schema = "dbo", catalog = "cpims_db")
public class User {
    private int id;
    private String username;
    private String password;

    @Id
    @Column(name = "USER_ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    @Basic
    @Column(name = "USER_USERNAME", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String userUsername) {
        this.username = userUsername;
    }

    @Basic
    @Column(name = "USER_PASSWORD", nullable = false, length = 40)
    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
