package model;


public class GroupMembership {

    private Integer id ;
    private Integer groupId ;
    private Integer studentId ;

    public GroupMembership(Integer id, Integer groupId, Integer studentId) {
        this.id = id ;
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

