package com.zhannicholas.cpwms.domain.repository;

import com.zhannicholas.cpwms.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param id 用户id
     * @return 和 id 对应的 user
     */
    User findById(int id);

    /**
     * @param username 用户名
     * @return 和 username 对应的用户
     */
    User findByUsername(String username);
}
