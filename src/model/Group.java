package model;

public class Group {

    private Integer id ;
    private String name ;
    private Integer assignmentId ;

    public Group(Integer id, String name, Integer assignmentId) {
        this.id = id;
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