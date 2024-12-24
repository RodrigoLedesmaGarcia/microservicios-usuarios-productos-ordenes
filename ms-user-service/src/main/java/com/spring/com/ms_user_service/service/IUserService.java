package com.spring.com.ms_user_service.service;

import com.spring.com.ms_user_service.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> listar();
    Optional<User> buscarPorId(Long id);
    User guardar (User user);
    void eliminar (Long id);

    List<User> findUserByName(String name);
}
