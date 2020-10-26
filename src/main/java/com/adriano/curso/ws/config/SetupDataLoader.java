package com.adriano.curso.ws.config;

import com.adriano.curso.ws.domain.Role;
import com.adriano.curso.ws.domain.User;
import com.adriano.curso.ws.repository.RoleRepository;
import com.adriano.curso.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;
import java.util.Optional;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
        Role roleUser = createRoleIfNotFound("ROLE_USER");

        User joao = new User("João", "Souza", "joao@gmail.com");
        User maria = new User("Maria", "Teixeira", "maria@gmail.com");

        joao.setRoles(Arrays.asList(roleAdmin));
        maria.setRoles(Arrays.asList(roleUser));
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

    private Role createRoleIfNotFound(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if(role.isPresent()){
            return role.get();
        }
        return roleRepository.save(new Role(name));
    }
}
