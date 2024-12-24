package com.spring.com.ms_user_service.repository;

import com.spring.com.ms_user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository  extends JpaRepository<User, Long> {

    List<User> findByName( String name);
}
