package Model.HighLevel;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, String> name;
	public static volatile SingularAttribute<Student, Integer> id;
	public static volatile ListAttribute<Student, Mark> marks;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String MARKS = "marks";

}

