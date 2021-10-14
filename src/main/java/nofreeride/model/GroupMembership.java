package nofreeride.model;

import javax.persistence.*;

@Entity
public class GroupMembership {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id ;
    private Integer groupId ;
    private Integer studentId ;

    private GroupMembership() {

    }

    public GroupMembership(Integer groupId, Integer studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getStudentId() {
        return studentId;
    }
}

