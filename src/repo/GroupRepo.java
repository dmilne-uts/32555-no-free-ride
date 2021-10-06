package repo;

import model.Assignment;
import model.Group;

import java.util.*;

public class GroupRepo {

    private Map<Integer,Group> groupsById ;

    public GroupRepo() {
        groupsById = new HashMap<>() ;
    }

    public void save(Group group) {
        this.groupsById.put(group.getId(), group) ;
    }

    public Group getById(Integer id) {
        return this.groupsById.get(id) ;
    }

    public Collection<Group> getAll() {
        return this.groupsById.values();
    }

    public List<Group> getGroupsForAssignment(Assignment assignment) {

        List<Group> groups = new ArrayList<>() ;

        for  (Group group:  this.groupsById.values()) {

            if (group.getAssignmentId().equals(assignment.getId())) {
                groups.add(group) ;
            }
        }

        return groups ;
    }

    public int getSize() {
        return this.groupsById.size() ;
    }
}


