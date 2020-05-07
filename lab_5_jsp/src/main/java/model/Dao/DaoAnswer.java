package model.Dao;


import model.Entities.Answer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Stateless
@Local
public class DaoAnswer{


    // TRANSACTION - when method called then container add new entityManager
    // when method finished (and transactions finished too) entityManager will be closed
    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public Answer get(int id) {
        return entityManager.find(Answer.class, id);
    }

    public List<Answer> getAll() {
        Query query = entityManager.createQuery("FROM ANSWERS");
        return query.getResultList();
    }

    public void save(Answer answer) {
        entityManager.persist(answer);
    }

    public void update(Answer answer) {
        entityManager.merge(answer);
    }

    public void delete(Answer answer) {
        entityManager.remove(answer);
    }
}
