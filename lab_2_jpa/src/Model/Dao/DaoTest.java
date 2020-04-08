package Model.Dao;

import Model.HighLevel.*;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Set;

public class DaoTest extends DaoAbstract implements Dao<Test> {

    @Override
    public Test get(long id) {
        return entityManager.find(Test.class, id);
    }

    @Override
    public List<Test> getAll() {
//        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Test> query = builder.createQuery(Test.class);
        Root<Test> testRoot = query.from(Test.class);
        Join<Test, Question> questionJoin = testRoot.join(Test_.questions);
        Join<Question, Answer> answerJoin = questionJoin.join(Question_.answers);
        //join does not mean the relationships will be fetched, to also fetch the related objects in the result use the fetch operation instead.
        query.distinct(true);
        TypedQuery<Test> typedQuery = entityManager.createQuery(query);

        List<Test> tests = typedQuery.getResultList();
//        entityManager.getTransaction().commit();
        return tests;//query.getResultList();
    }

    @Override
    public void save(Test test) {
        executeInsideTransaction(entityManager -> entityManager.persist(test));
    }

    @Override
    public void update(Test test) {
        executeInsideTransaction(entityManager -> entityManager.merge(test));
    }

    @Override
    public void delete(Test test) {
        executeInsideTransaction(entityManager -> entityManager.remove(test));
    }
}
