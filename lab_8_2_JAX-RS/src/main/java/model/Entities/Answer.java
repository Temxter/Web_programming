package model.Entities;

import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)

@Entity(name = "ANSWERS")
public class Answer implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;
    @XmlAttribute

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @XmlAttribute

    private String answer;
    @XmlAttribute

    private boolean isRight;
    @XmlTransient

    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Answer(int id, String answer, boolean isRight) {
        this.id = id;
        this.answer = answer;
        this.isRight = isRight;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isRight() {
        return isRight;
    }

    @Override
    public String toString() {
        return answer;
    }

    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        this.question = (Question) parent;
    }
}
