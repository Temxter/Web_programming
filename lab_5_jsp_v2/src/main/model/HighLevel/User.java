package model.HighLevel;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name= "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;
    // ORDINAL = write to DB 1, 2, 3...
    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;

    @OneToOne
    private Student student;

    public User(String login, String password, UserType userType, Student student) {
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
