package Model.Dao;

import Model.HighLevel.Mark;
import Model.HighLevel.Student;
import Model.HighLevel.Student_;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class DaoStudent extends  DaoAbstract implements Dao<Student> {

    @Override
    public Student get(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
//        CriteriaBuilder builder= entityManager.getCriteriaBuilder();
//        CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
//        Root<Student> studentRoot = criteriaQuery.from(Student.class);
//        Join<Student, Mark> markJoin = studentRoot.join(Student_.marks);
//        criteriaQuery.distinct(true);
//        TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
//        return typedQuery.getResultList();

        return entityManager.createQuery("from STUDENTS ").getResultList();
    }

    @Override
    public void save(Student student) {
        executeInsideTransaction(entityManager -> entityManager.persist(student));
    }

    @Override
    public void update(Student student) {
        executeInsideTransaction(entityManager -> entityManager.merge(student));
    }

    @Override
    public void delete(Student student) {
        executeInsideTransaction(entityManager -> entityManager.remove(student));
    }



}
