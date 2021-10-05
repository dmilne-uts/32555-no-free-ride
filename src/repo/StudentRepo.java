package repo;

import model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentRepo {

    private Map<Integer, Student> studentsById ;

    public StudentRepo() {
        studentsById = new HashMap<>() ;
    }

    public void save(Student student) {
        this.studentsById.put(student.getId(), student) ;
    }

    public Student getById(Integer id) {
        return this.studentsById.get(id) ;
    }


    public Collection<Student> getAll() {
        return this.studentsById.values();
    }

    public int getSize() {
        return this.studentsById.size() ;
    }
}
