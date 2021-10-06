import model.Group;
import model.GroupMembership;
import model.Student;
import repo.GroupMembershipRepo;
import repo.GroupRepo;
import repo.StudentRepo;

public class App {

    private StudentRepo students = new StudentRepo();
    private GroupRepo groups = new GroupRepo() ;
    private GroupMembershipRepo memberships = new GroupMembershipRepo() ;
    
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

    private void setupGroups() {

        System.out.println("Setting up groups") ;

        Group group ;
        GroupMembership membership ;

        group = new Group(groups.getSize(), "The unimaginatively named") ;
        groups.save(group);

        //adding Bob Bobson
        membership = new GroupMembership(memberships.getSize(), group.getId(), 1234567) ;
        memberships.save(membership);

        //adding James Jameson
        membership = new GroupMembership(memberships.getSize(), group.getId(), 2345678) ;
        memberships.save(membership);

        //adding Jeff Jefferson
        membership = new GroupMembership(memberships.getSize(), group.getId(), 3456789) ;
        memberships.save(membership);

        /*
        To accomplish Task 2, you can add groups and memberships  by replicating the lines above, and simply changing
        the values you pass in to the respective constructors.

        For example:
         */

        //creating a second group
        group = new Group(groups.getSize(), "The randomly named") ;
        groups.save(group);

        //adding Nishat
        membership = new GroupMembership(memberships.getSize(), group.getId(), 4567890) ;
        memberships.save(membership);

        //adding Sumayya
        membership = new GroupMembership(memberships.getSize(), group.getId(), 5678901) ;
        memberships.save(membership);

        //adding Emma-Louise
        membership = new GroupMembership(memberships.getSize(), group.getId(), 6789012) ;
        memberships.save(membership);

    }



    private void printStudents() {

        System.out.println("There are " + students.getSize() + " students: ");

        for (Student student: students.getAll()) {
            System.out.println("  " + student) ;
        }
    }

    private void printGroups() {

        System.out.println("There are " + groups.getSize() + " groups: ");

        for (Group group: groups.getAll()) {
            System.out.println("  " + group) ;

            for (Integer studentId: memberships.getStudentIds(group)) {
                Student member = students.getById(studentId) ;
                System.out.println("    " + member) ;
            }
        }
    }


    public static void main(String args[]) {

        App app = new App() ;

        app.setupStudents();
        app.printStudents();

        app.setupGroups();
        app.printGroups();
    }
}

