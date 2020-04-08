package Model.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;

abstract public class DaoAbstract {
    EntityManager entityManager = JpaManager.getInstance();

    protected void executeInsideTransaction(Consumer<EntityManager> action){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            action.accept(entityManager);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }
}
