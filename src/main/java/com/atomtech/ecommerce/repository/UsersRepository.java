package com.atomtech.ecommerce.repository;


import com.atomtech.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findById(long user_id);

    Users findByLoginAndPwd(String login, String pwd);

    Users findByLogin(String login);
}
