package nofreeride.repo;

import nofreeride.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Integer> {

}
