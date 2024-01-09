package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositoties.RoleRepository;
import ru.kata.spring.boot_security.demo.repositoties.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
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
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAutorities(user.getRoles()));
    }

    private Collection<? extends SimpleGrantedAuthority> mapRolesToAutorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
    public List<Role> listRoles() {return roleRepository.findAll();}
    public List<User> list() {
        return userRepository.findAll();
    }

    public User find(Long id) {
        return userRepository.getById(id);
    }
    public void save(User user) {
        if (!user.getName().isBlank() && !user.getLastname().isBlank()) {
        userRepository.save(user);}
    }
    public void delete(Long id) {
        userRepository.delete(find(id));
    }
    public void update(User user) {
        if (!user.getName().isBlank() && !user.getLastname().isBlank()) {
            userRepository.save(user);}
    }
}