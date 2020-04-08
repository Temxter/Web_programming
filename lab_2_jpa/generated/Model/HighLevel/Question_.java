package Model.HighLevel;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Question.class)
public abstract class Question_ {

	public static volatile SingularAttribute<Question, String> question;
	public static volatile SingularAttribute<Question, Test> test;
	public static volatile ListAttribute<Question, Answer> answers;
	public static volatile SingularAttribute<Question, Integer> id;

	public static final String QUESTION = "question";
	public static final String TEST = "test";
	public static final String ANSWERS = "answers";
	public static final String ID = "id";

}

