import model.*;
import model.reviewing.*;
import repo.*;
import service.*;

public class App {

    private StudentRepo students = new StudentRepo();
    private GroupRepo groups = new GroupRepo() ;
    private GroupMembershipRepo memberships = new GroupMembershipRepo() ;
    private AssignmentRepo assignments = new AssignmentRepo() ;
    private ReviewRepo reviews = new ReviewRepo() ;

    private void setupStudents() {

        System.out.println("Setting up students") ;

        Student student ;

        student = new Student(1234567, "Bob", "Bobson") ;
        students.save(student) ;

        student = new Student(2345678, "James", "Jameson") ;
        students.save(student) ;

        student = new Student( 3456789, "Jeff", "Jefferson") ;
        students.save(student) ;

        student = new Student(4567890, "Nishat", "Hartley") ;
        students.save(student) ;

        student = new Student(5678901, "Sumayya", "Milner") ;
        students.save(student) ;

        student = new Student(6789012, "Emma-Louise", "Cohen") ;
        students.save(student) ;


        // Adding a whole bunch more students, so we can have multiple assignments and multiple groups




        student = new Student(9000001, "Haydon", "Lovell") ;
        students.save(student) ;

        student = new Student(9000002, "Imogen", "Cardenas") ;
        students.save(student) ;

        student = new Student(9000003, "Samira", "Parra") ;
        students.save(student) ;


        student = new Student(9000004, "Lavinia", "Lin") ;
        students.save(student) ;

        student = new Student(9000005, "Mikael", "Whitaker") ;
        students.save(student) ;

        student = new Student(9000006, "Felicity", "Parrish") ;
        students.save(student) ;

        System.out.println("Finished setting up students") ;
    }

    /*

    Altering this function to set up assignments and groups simultaneously
     */
    private void setupAssignmentsAndGroups() {

        System.out.println("Setting up assignments and groups") ;

        Assignment assignment ;
        Group group ;
        GroupMembership membership ;


        assignment = new Assignment(assignments.getSize(), "Report on ethical implications of new technology") ;
        assignments.save(assignment);

        group = new Group(groups.getSize(), "The unimaginatively named", assignment.getId()) ;
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


        //creating a second group
        group = new Group(groups.getSize(), "The randomly named", assignment.getId()) ;
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


        //second assignment
        assignment = new Assignment(assignments.getSize(), "Software development project") ;
        assignments.save(assignment);

        //third group
        group = new Group(groups.getSize(), "The A Team", assignment.getId()) ;
        groups.save(group);

        //since we don't need to do anyhing with a group membership except save it, we can take some shortcuts:
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 9000001));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 9000002));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 9000003));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 3456789));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 1234567));

        //fourth group
        group = new Group(groups.getSize(), "The Them", assignment.getId()) ;
        groups.save(group);

        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 9000004));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 9000005));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 9000006));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 2345678));
        memberships.save(new GroupMembership(memberships.getSize(), group.getId(), 6789012));

    }



    private void printStudents() {

        System.out.println("There are " + students.getSize() + " students: ");

        for (Student student: students.getAll()) {
            System.out.println("  " + student) ;
        }
    }

    private void printAssignmentsAndGroups() {

        System.out.println("There are " + groups.getSize() + " groups across " + assignments.getSize() + " assignments: ");

        for (Assignment assignment: assignments.getAll()) {

            System.out.println(assignment) ;

            for (Group group: groups.getGroupsForAssignment(assignment)) {

                System.out.println("  " + group) ;

                for (Integer studentId: memberships.getStudentIds(group)) {
                    Student member = students.getById(studentId) ;
                    System.out.println("    " + member) ;
                }

            }

        }

    }

    private void printReviewQuestions() {

        System.out.println(CommunicationAmount.question) ;
        for (CommunicationAmount ca: CommunicationAmount.values()) {
            System.out.println("  (" + ca + ") " + ca.getDescription()) ;
        }

        System.out.println(LowCommunicationReason.question) ;
        for (int i=0 ; i<LowCommunicationReason.values().length ; i ++) {
            System.out.println("  (" + LowCommunicationReason.values()[i] + ") " + LowCommunicationReason.descriptions[i]) ;
        }

        System.out.println(MeetingContribution.question) ;
        for (int i=0 ; i<MeetingContribution.values().length ; i ++) {
            System.out.println("  (" + MeetingContribution.values()[i] + ") " + MeetingContribution.descriptions[i]) ;
        }

        System.out.println(ContributionAmount.question) ;
        for (int i=0 ; i<ContributionAmount.values().length ; i ++) {
            System.out.println("  (" + ContributionAmount.values()[i] + ") " + ContributionAmount.descriptions[i]) ;
        }
    }

    private void setupReviews() {

        InteractiveReviewer reviewer = new InteractiveReviewer() ;

        Review review ;

        review = reviewer.createReview(reviews.getSize(), "Bob") ;
        reviews.save(review) ;
/*

        review = reviewer.createReview(reviews.getSize(), "James") ;
        reviews.save(review) ;


        review = reviewer.createReview(reviews.getSize(), "Jeff") ;
        reviews.save(review) ;

*/
    }

    private void printReviews() {

        for (Review review: reviews.getAll()) {

            System.out.println("Review " + review.getId() + "(score = " + review.getScore() + ")");

            System.out.println("  " + review.getCommunicationAmount().getDescription()) ;

            if (review.getLowCommunicationReason() != null)
                System.out.println("  " + review.getLowCommunicationReason().getDescription()) ;

            if (review.getMeetingContribution() != null)
                System.out.println("  " + review.getMeetingContribution().getDescription()) ;

            System.out.println("  " + review.getContributionAmount().getDescription()) ;

            if (review.getContributionDetails() != null)
                System.out.println("  " + review.getContributionDetails()) ;
        }
    }


    public static void main(String args[]) {

        App app = new App() ;

        app.setupStudents();
        app.printStudents();

        app.setupAssignmentsAndGroups();
        app.printAssignmentsAndGroups();

        app.printReviewQuestions();

        app.setupReviews();
        app.printReviews();
    }
}

