package model.Dao;

import model.HighLevel.Student;
import model.HighLevel.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

public class DaoUser implements Dao<User> {

    @PersistenceContext(name = "questionUnit", type = PersistenceContextType.EXTENDED)
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
        if (users == null)
            return null;
        //if (users.size() == 1)
        return users.get(0);
    }

    public User getUserByLogin(String login)  {
        Query query = entityManager.createQuery("FROM USERS WHERE login = :login");
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        if (users == null)
            return null;
        return users.get(0);
    }


    @Override
    public User get(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        Query query = entityManager.createQuery("FROM USERS");
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
