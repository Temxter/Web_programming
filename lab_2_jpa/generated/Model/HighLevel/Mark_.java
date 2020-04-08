package Model.HighLevel;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mark.class)
public abstract class Mark_ {

	public static volatile SingularAttribute<Mark, Date> date;
	public static volatile SingularAttribute<Mark, Test> test;
	public static volatile SingularAttribute<Mark, Student> student;
	public static volatile SingularAttribute<Mark, Integer> rightAnswers;
	public static volatile SingularAttribute<Mark, Integer> id;

	public static final String DATE = "date";
	public static final String TEST = "test";
	public static final String STUDENT = "student";
	public static final String RIGHT_ANSWERS = "rightAnswers";
	public static final String ID = "id";

}

