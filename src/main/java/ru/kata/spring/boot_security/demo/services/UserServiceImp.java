package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositoties.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not founs " + username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User find(Long id) {
        return userRepository.getById(id);
    }
    public void save(User user) {
        if (!user.getName().isBlank() && !user.getLastname().isBlank()) {
            if (!findUserByUsername(user.getUsername()).equals(user)){
                    userRepository.save(user);}}
    }
    public void delete(Long id) {
        userRepository.delete(find(id));
    }
}