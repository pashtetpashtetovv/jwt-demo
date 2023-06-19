package com.example.jwtdemo.config.security;

import com.example.jwtdemo.model.ERole;
import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.service.RoleService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DbInsert implements ApplicationRunner {

    private final RoleService roleService;

    public DbInsert(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = new LinkedList<>();
        roles.add(new Role(ERole.ROLE_ADMIN));
        roles.add(new Role(ERole.ROLE_USER));
        roles.add(new Role(ERole.ROLE_MODERATOR));
        roles = roles.stream()
                .filter(role -> !roleService.existsByName(role.getName()))
                .toList();
        roleService.saveAll(roles);
    }
}
