package model.Dao;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
@Stateless
public class EntityManagerExecutor {
    @PersistenceContext(name = "questionUnit")
    EntityManager entityManager;

    public void execute(String sqlStatement){
        // executeUpdate() - as commit
        entityManager.createNativeQuery(sqlStatement).executeUpdate();
    }
}
