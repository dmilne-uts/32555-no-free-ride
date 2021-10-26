package nofreeride;

import nofreeride.gui.ManageStudents;
import nofreeride.model.*;
import nofreeride.model.reviewing.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import nofreeride.repo.*;
import nofreeride.service.*;

@SpringBootApplication
public class App implements InitializingBean {

    @Autowired
    private StudentRepo students ;

    @Autowired
    private GroupRepo groups ;

    @Autowired
    private GroupMembershipRepo memberships ;

    @Autowired
    private AssignmentRepo assignments ;

    @Autowired
    private ReviewRepo reviews ;

    @Autowired
    private InteractiveReviewer reviewer ;

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

        assignment = retrieveOrCreateAssignment( "Report on ethical implications of new technology") ;

        group = retrieveOrCreateGroup(assignment, "The unimaginatively named") ;
        createGroupMemberIfNotExists(group, 1234567) ;
        createGroupMemberIfNotExists(group, 2345678) ;
        createGroupMemberIfNotExists(group, 3456789) ;

        group = retrieveOrCreateGroup(assignment, "The randomly named") ;
        createGroupMemberIfNotExists(group, 4567890) ;
        createGroupMemberIfNotExists(group, 5678901) ;
        createGroupMemberIfNotExists(group, 6789012) ;


        assignment = retrieveOrCreateAssignment("Software development project") ;

        group = retrieveOrCreateGroup(assignment, "The A Team") ;
        createGroupMemberIfNotExists(group, 9000001);
        createGroupMemberIfNotExists(group, 9000002);
        createGroupMemberIfNotExists(group, 9000003);
        createGroupMemberIfNotExists(group, 3456789);
        createGroupMemberIfNotExists(group, 1234567);

        group = retrieveOrCreateGroup( assignment,"The Them") ;
        createGroupMemberIfNotExists(group, 9000004);
        createGroupMemberIfNotExists(group, 9000005);
        createGroupMemberIfNotExists(group, 9000006);
        createGroupMemberIfNotExists(group, 2345678);
        createGroupMemberIfNotExists(group, 6789012);
    }

    private Assignment retrieveOrCreateAssignment(String name) {

        Assignment assignment = assignments.findByName(name) ;

        if (assignment == null) {
            assignment = new Assignment(name) ;
            assignments.save(assignment) ;
        }

        return assignment;
    }

    private Group retrieveOrCreateGroup(Assignment assignment, String name) {

        Group group = groups.findByAssignmentIdAndName(assignment.getId(), name) ;
        if (group == null) {
            group = new Group(name, assignment.getId()) ;
            groups.save(group) ;
        }

        return group ;
    }

    private void createGroupMemberIfNotExists(Group group, int studentId) {

        GroupMembership membership = memberships.findByStudentIdAndGroupId(studentId, group.getId()) ;

        if (membership == null) {
            membership = new GroupMembership(group.getId(), studentId) ;
            memberships.save(membership) ;
        }
    }


    private void printStudents() {

        System.out.println("There are " + students.count() + " students: ");

        for (Student student: students.findAll()) {
            System.out.println("  " + student) ;
        }
    }

    private void printAssignmentsAndGroups() {

        System.out.println("There are " + groups.count() + " groups across " + assignments.count() + " assignments: ");

        for (Assignment assignment: assignments.findAll()) {

            System.out.println(assignment) ;

            for (Group group: groups.findByAssignmentId(assignment.getId())) {

                System.out.println("  " + group) ;

                for (GroupMembership membership: memberships.findByGroupId(group.getId())) {
                    Student member = students.findById(membership.getStudentId()).get() ;
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

    private void setupReview() {

        Review review = reviewer.createReview() ;
        reviews.save(review) ;

    }

    private void printReviews() {

        for (Review review: reviews.findAll()) {

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
    	
    	SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        setupStudents();
        printStudents();

        setupAssignmentsAndGroups();
        printAssignmentsAndGroups();

        printReviewQuestions();

        //setupReview();
        //printReviews();
        
        //ReviewViewer reviewViewer = new ReviewViewer() ;
        //reviewViewer.pack();
        //reviewViewer.setVisible(true);
        
        ManageStudents gui = new ManageStudents(this.students);  //(this.students) ;
        gui.pack();
        gui.setVisible(true);
    }
}

