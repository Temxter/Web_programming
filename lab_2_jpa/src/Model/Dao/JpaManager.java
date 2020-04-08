package Model.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaManager {
    static private EntityManager entityManager;
    static private EntityManagerFactory entityManagerFactory;

    private JpaManager(){
    }

    public static EntityManager getInstance(){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("questionUnit");
        }
        if (entityManager == null){
            entityManager = entityManagerFactory.createEntityManager();
        }
        return  entityManager;
    }

}
