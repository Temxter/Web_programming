package model.Dao;

import model.Entities.User;

import javax.ejb.EnterpriseBean;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.List;

@Stateless
@Local
public class DaoUser {

    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public User authorizationUser(String login, String password)  {
        Query query = entityManager.createQuery("FROM USERS WHERE login = :login AND " +
                "password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        // DB must check
        //        if (users.size() > 1)
        //            throw new DaoException("Users with equals login and password more then one. Amount = " + users.size());
        if (users == null || users.isEmpty())
            return null;
        //if (users.size() == 1)
        return users.get(0);
    }

    public User getUserByLogin(String login)  {
        Query query = entityManager.createQuery("FROM USERS WHERE login = :login");
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        if (users == null || users.isEmpty())
            return null;
        return users.get(0);
    }

    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAll() {
        Query query = entityManager.createQuery("FROM USERS");
        return query.getResultList();
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(User user) {
        entityManager.remove(user);
    }
}
