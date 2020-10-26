package com.adriano.curso.ws.config;

import com.adriano.curso.ws.domain.User;
import com.adriano.curso.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Optional;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User joao = new User("João", "Souza", "joao@gmail.com");
        User maria = new User("Maria", "Teixeira", "maria@gmail.com");

        createUserIfNotFound(joao);
        createUserIfNotFound(maria);
    }

    private User createUserIfNotFound(final User user){
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser.isPresent()){
            return savedUser.get();
        }
        return userRepository.save(user);
    }
}
