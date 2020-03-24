package com.example.spring_junit.dao;

import com.example.spring_junit.model.Role;
import com.example.spring_junit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private EntityManagerFactory emf;
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("FROM User u");
        List<User> list = query.getResultList();
        return list;
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        try {
            if (!isRoleContains(user)) {
                for (Role r : user.getRole()) {
                    entityManager.persist(r);
                }
            }

//            user.setRole(getRoleByName(user.getRole()));

            entityManager.persist(user);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isRoleContains(User user) {
        Query query = entityManager.createQuery("FROM Role r");
        List<Role> list = query.getResultList();
        for (Role r : list) {
            for (Role rUser : user.getRole()) {
                if (r.getName().toUpperCase().equals(rUser.getName().toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean addRole(Role role) {
        try {
            entityManager.persist(role);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Collection<Role> getRoleByName(Collection<String> roles) {
        Collection<Role> result = new ArrayList<>();
        try {

        for (String name : roles) {
            try {
                Query query = entityManager.createQuery("FROM Role r WHERE r.name = :name");
                query.setParameter("name", name);
                result.add((Role) query.getSingleResult());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            Role newRole = new Role(name);
            addRole(newRole);
            name = newRole.getName();
            Query query = entityManager.createQuery("FROM Role r WHERE r.name = :name");
            query.setParameter("name", name);
            result.add((Role) query.getSingleResult());
        }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Transactional
    @Override
    public boolean deleteUser(String id) {
        try {
            Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :id");
            query.setParameter("id", Long.parseLong(id));
            query.executeUpdate();
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, Collection<Role> roles, String login, String password) {
        try {
            entityManager.merge(new User(Long.parseLong(id), firstName, lastName, login, password, Long.parseLong(phoneNumber), roles));
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateUser(String id, String firstName, String lastName, String phoneNumber, Collection<Role> roles, String login) {
        try {
            entityManager.merge(new User(Long.parseLong(id), firstName, lastName, login, getUserById(id).getPassword(), Long.parseLong(phoneNumber), roles));
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Transactional
    @Override
    public boolean updateUser(User user) {
        try {
            entityManager.merge(user);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkAuth(String login, String password) {
        try {
            Query query = entityManager.createQuery("FROM User u WHERE u.login = :login AND u.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            return query.getSingleResult() != null;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        user.setLogin("");
        try {
            Query query = entityManager.createQuery("FROM User u WHERE login = :login");
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserById(String id) {
        try {
            Query query = entityManager.createQuery("FROM User u WHERE u.id = :id");
            query.setParameter("id", Long.parseLong(id));
            return (User) query.getSingleResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
