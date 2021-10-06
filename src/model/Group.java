package model;

public class Group {

    private Integer id ;
    private String name ;

    public Group(Integer id, String name) {
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