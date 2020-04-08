package Model.Dao;

import Model.HighLevel.Answer;
import Model.HighLevel.Student;

import javax.persistence.Query;
import java.util.List;

public class DaoAnswer extends DaoAbstract implements Dao<Answer> {
    @Override
    public Answer get(long id) {
        return entityManager.find(Answer.class, id);
    }

    @Override
    public List<Answer> getAll() {
        Query query = entityManager.createQuery("FROM ANSWERS");
        return query.getResultList();
    }

    @Override
    public void save(Answer answer) {
        executeInsideTransaction(entityManager -> entityManager.persist(answer));
    }

    @Override
    public void update(Answer answer) {
        executeInsideTransaction(entityManager -> entityManager.merge(answer));
    }

    @Override
    public void delete(Answer answer) {
        executeInsideTransaction(entityManager -> entityManager.remove(answer));
    }
}
