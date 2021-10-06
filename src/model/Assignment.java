package model;

public class Assignment {

    private Integer id ;

    private String name ;

    public Assignment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.id + ": " + this.name ;
    }
}
