package com.spring.com.ms_user_service.service;

import com.spring.com.ms_user_service.model.User;
import com.spring.com.ms_user_service.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public List<User> listar() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User guardar(User user) {
        return userRepository.save(user);
    }

    @Override
    public void eliminar(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public List<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }
}
