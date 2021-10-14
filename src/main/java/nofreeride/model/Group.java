package nofreeride.model;

import javax.persistence.*;

@Entity
//We  need to specify a table name because "group" is unfortunately a reserved SQL keyword
@Table(name = "studentGroup")
public class Group {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id ;

    private String name ;
    private Integer assignmentId ;

    private Group() {

    }

    public Group(String name, Integer assignmentId) {
        this.name = name;
        this.assignmentId = assignmentId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public String toString() {
        return this.id + ": " + this.name ;
    }
}