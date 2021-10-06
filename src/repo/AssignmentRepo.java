package repo;

import model.Assignment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AssignmentRepo {

    private Map<Integer, Assignment> assignmentsById ;

    public AssignmentRepo() {
        this.assignmentsById = new HashMap<>() ;
    }

    public void save(Assignment assignment) {
        this.assignmentsById.put(assignment.getId(), assignment);
    }

    public Assignment getById(Integer id) {
        return this.assignmentsById.get(id) ;
    }

    public Assignment getByName(String name) {
        for (Assignment assignment: this.assignmentsById.values()) {
            if (assignment.getName().equals(name))
                return assignment;
        }

        return null ;
    }

    public Collection<Assignment> getAll() {
        return assignmentsById.values();
    }

    public int getSize() {
        return assignmentsById.size() ;
    }
}
