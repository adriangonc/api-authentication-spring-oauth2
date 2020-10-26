package com.adriano.curso.ws.service;

import com.adriano.curso.ws.domain.User;
import com.adriano.curso.ws.repository.UserRepository;
import com.adriano.curso.ws.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
    }

}
