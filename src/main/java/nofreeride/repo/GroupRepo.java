package nofreeride.repo;

import nofreeride.model.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GroupRepo extends CrudRepository<Group, Integer> {

    List<Group> findByAssignmentId(int assignmentId) ;

    Group findByAssignmentIdAndName(int assignmentId, String name) ;
}


