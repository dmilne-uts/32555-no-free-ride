import model.Student;
import repo.StudentRepo;

public class App {

    private StudentRepo students = new StudentRepo();


    private void setupStudents() {

        System.out.println("Setting up students") ;

        Student student ;

        student = new Student(1234567, "Bob", "Bobson") ;
        students.save(student) ;

        student = new Student(2345678, "James", "Jameson") ;
        students.save(student) ;

        student = new Student( 3456789, "Jeff", "Jefferson") ;
        students.save(student) ;

        /*
        To accomplish Task 1, you can add additional students by replicating the lines above, and simply changing
        the values you pass in to the student constructor.

        For example:
         */

        student = new Student(4567890, "Nishat", "Hartley") ;
        students.save(student) ;

        student = new Student(5678901, "Sumayya", "Milner") ;
        students.save(student) ;

        student = new Student(6789012, "Emma-Louise", "Cohen") ;
        students.save(student) ;


        System.out.println("Finished setting up students") ;
    }



    private void printStudents() {

        System.out.println("There are " + students.getSize() + " students: ");

        for (Student student: students.getAll()) {
            System.out.println("  " + student) ;
        }
    }


    public static void main(String args[]) {

        App app = new App() ;

        app.setupStudents();
        app.printStudents();

    }
}

