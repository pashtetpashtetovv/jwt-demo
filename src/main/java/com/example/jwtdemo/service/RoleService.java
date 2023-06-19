package com.example.jwtdemo.service;

import com.example.jwtdemo.model.ERole;
import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public List<Role> saveAll(List<Role> roles){
        return roleRepository.saveAll(roles);
    }

    public Boolean existsByName(ERole name){
        return roleRepository.existsByName(name);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
