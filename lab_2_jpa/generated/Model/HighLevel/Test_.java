package Model.HighLevel;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Test.class)
public abstract class Test_ {

	public static volatile SingularAttribute<Test, String> name;
	public static volatile ListAttribute<Test, Question> questions;
	public static volatile SingularAttribute<Test, Integer> id;

	public static final String NAME = "name";
	public static final String QUESTIONS = "questions";
	public static final String ID = "id";

}

