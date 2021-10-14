package nofreeride.model;

import javax.persistence.*;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id ;

    private String name ;

    private Assignment() {

    }

    public Assignment(String name) {
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
