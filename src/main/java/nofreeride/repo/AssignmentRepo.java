package nofreeride.repo;

import nofreeride.model.Assignment;
import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepo extends CrudRepository<Assignment, Integer> {

    Assignment findByName(String name) ;

}
