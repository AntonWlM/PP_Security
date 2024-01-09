package ru.kata.spring.boot_security.demo.models;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private int age;
    @NotNull
    private String password;
    @NotNull
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
    public User(Long id, String name, String lastname, int age, String password, String username, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }
    public User() {}

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
