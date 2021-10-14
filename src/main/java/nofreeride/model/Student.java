package nofreeride.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    private Integer id ;

    private String firstName ;
    private String lastName ;

    private Student() {

    }

    public Student(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return id + ": " + lastName + ", " + firstName ;
    }
}