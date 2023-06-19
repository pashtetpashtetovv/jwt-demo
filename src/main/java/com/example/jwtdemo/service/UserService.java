package com.example.jwtdemo.service;

import com.example.jwtdemo.exception.NotFoundException;
import com.example.jwtdemo.model.ERole;
import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.repository.RoleRepository;
import com.example.jwtdemo.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(NotFoundException::new);
    }

    public UserDetails loadUserLazyByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findLazyByUsername(username).orElseThrow(NotFoundException::new);
    }

    public Boolean isUserExistsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    private User save(User user){
        return userRepository.save(user);
    }

    public User save(String username, String email, String password, Set<ERole> roles){
        password = passwordEncoder.encode(password);
        User user = new User(username, email, password);
        Set<Role> mappedRoles =
                roles.stream()
                        .map(roleRepository::findByName)
                                .collect(Collectors.toSet());
        user.setRoles(mappedRoles);
        return save(user);
    }

}
