package model.Dao;


import model.Entities.Question;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless
@Local
public class DaoQuestion {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public Question get(int id) {
        return entityManager.find(Question.class, id);
    }

    public List<Question> getAll() {
        Query query = entityManager.createQuery("FROM QUESTIONS");
        return query.getResultList();
    }

    public void save(Question question) {
        entityManager.persist(question);
    }

    public void update(Question question) {
        entityManager.merge(question);
    }

    public void delete(Question question) {
        entityManager.remove(question);
    }
}
