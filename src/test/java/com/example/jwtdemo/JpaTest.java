package com.example.jwtdemo;

import com.example.jwtdemo.model.User;
import com.example.jwtdemo.repository.RoleRepository;
import com.example.jwtdemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
public class JpaTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRoles(){
        assertThat(roleRepository.findAll().size() == 3);
    }

    @Test
    public void testUsers(){
        User user = new User("User", "user@mail.ru", "123");
        userRepository.saveAndFlush(user);
        assertThat(userRepository.existsByUsername("user"));
    }

}
