package repo;

import model.Group;
import model.GroupMembership;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMembershipRepo {

    private Map<Integer,GroupMembership> membershipsById ;

    public GroupMembershipRepo() {
        this.membershipsById = new HashMap<>();
    }

    public void save(GroupMembership membership) {
        this.membershipsById.put(membership.getId(), membership);
    }

    public List<Integer> getGroupIds(Student student) {

        List<Integer> groupIds = new ArrayList<>() ;

        for (GroupMembership membership: this.membershipsById.values()) {

            if (membership.getStudentId().equals(student.getId())) {
                groupIds.add(membership.getGroupId()) ;
            }
        }

        return groupIds ;
    }

    public List<Integer> getStudentIds(Group group) {

        List<Integer> studentIds = new ArrayList<>() ;

        for (GroupMembership membership: this.membershipsById.values()) {

            if (membership.getGroupId().equals(group.getId())) {
                studentIds.add(membership.getStudentId()) ;
            }
        }

        return studentIds ;
    }

    public int getSize() {
        return this.membershipsById.size() ;
    }
}
