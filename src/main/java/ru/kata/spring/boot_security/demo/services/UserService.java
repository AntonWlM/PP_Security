package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import javax.transaction.Transactional;
import java.util.List;


public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    void save(User user);

    List<User> list();

    User find(Long id);

    void delete(Long id);
}
