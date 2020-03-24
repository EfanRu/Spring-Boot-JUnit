package com.example.spring_junit.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "role_id", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private Collection<User> users;

    public Role() {}

    public Role(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name.toUpperCase(), role.name.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
