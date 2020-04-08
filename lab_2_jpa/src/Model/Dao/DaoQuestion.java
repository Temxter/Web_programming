package Model.Dao;

import Model.HighLevel.Question;
import Model.HighLevel.Student;

import javax.persistence.Query;
import java.util.List;

public class DaoQuestion extends DaoAbstract implements Dao<Question> {
    @Override
    public Question get(long id) {
        return entityManager.find(Question.class, id);
    }

    @Override
    public List<Question> getAll() {
        Query query = entityManager.createQuery("FROM QUESTIONS");
        return query.getResultList();
    }

    @Override
    public void save(Question question) {
        executeInsideTransaction(entityManager -> entityManager.persist(question));
    }

    @Override
    public void update(Question question) {
        executeInsideTransaction(entityManager -> entityManager.merge(question));
    }

    @Override
    public void delete(Question question) {
        executeInsideTransaction(entityManager -> entityManager.remove(question));
    }
}
