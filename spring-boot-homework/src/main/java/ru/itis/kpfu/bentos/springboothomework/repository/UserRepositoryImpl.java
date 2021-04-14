package ru.itis.kpfu.bentos.springboothomework.repository;

import org.springframework.stereotype.Repository;
import ru.itis.kpfu.bentos.springboothomework.models.User;
import ru.itis.kpfu.bentos.springboothomework.repository.interfaces.UserRepositoryEM;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryEM {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public User getById(Long userID) {
        User user = em.find(User.class, userID);
        em.detach(user);
        return user;
    }

    @Override
    public List findAll() {
        return em.createQuery("SELECT user FROM User user").getResultList();
    }
}
