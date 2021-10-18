package nofreeride.service;

import nofreeride.model.*;
import nofreeride.model.reviewing.CommunicationAmount;
import nofreeride.model.reviewing.ContributionAmount;
import nofreeride.model.reviewing.LowCommunicationReason;
import nofreeride.model.reviewing.MeetingContribution;
import nofreeride.repo.AssignmentRepo;
import nofreeride.repo.GroupMembershipRepo;
import nofreeride.repo.GroupRepo;
import nofreeride.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class InteractiveReviewer {

    private Scanner scanner = new Scanner(System.in) ;

    @Autowired
    private StudentRepo students ;

    @Autowired
    private AssignmentRepo assignments ;

    @Autowired
    private GroupRepo groups ;

    @Autowired
    private GroupMembershipRepo memberships ;


    private Group askGroup() {

        while (true) {
            System.out.println("Please select a group:");

            for (Group g: groups.findAll()) {
                System.out.println("(" + g.getId() + ") " + g.getName()) ;
            }

            Integer groupId = scanner.nextInt();
            scanner.nextLine();

            Optional<Group> result = groups.findById(groupId) ;

            if (result.isPresent()) {
                return result.get() ;
            }

            System.out.println("Sorry, I don't know which group that is. Please try again") ;
        }

    }

    private Student askGroupMember(Group group, String prompt) {

        //get details of all members
        List<Student> members = new ArrayList<>();
        for (GroupMembership member: memberships.findByGroupId(group.getId())) {
            Student student = students.findById(member.getStudentId()).get() ;
            members.add(student) ;
        }

        //choose a member
        Student member = null ;
        while (member == null) {
            System.out.println(prompt);

            for (int index = 0 ; index< members.size() ; index++) {
                Student s = members.get(index);

                System.out.println("(" + index + ") " + s.getFirstName() + " " + s.getLastName());
            }

            int selectedIndex = scanner.nextInt();
            scanner.nextLine();

            if (selectedIndex < 0 || selectedIndex >= members.size()) {
                System.out.println("Sorry, I don't know who that is. Please try again") ;
            } else {
                member = members.get(selectedIndex);
            }
        }

        return member ;
    }



    private CommunicationAmount askCommunicationAmount(String name) {

        System.out.println(CommunicationAmount.question.replaceAll("NAME", name)) ;
        for (int i=0 ; i<CommunicationAmount.descriptions.length ; i++) {
            System.out.println("(" + i + ") " + CommunicationAmount.descriptions[i]);
        }
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();

        return  CommunicationAmount.values()[selectedIndex] ;
    }

    private LowCommunicationReason askLowCommunicationReason(String name) {

        System.out.println(LowCommunicationReason.question.replaceAll("NAME", name)) ;
        for (int i = 0; i<LowCommunicationReason.descriptions.length ; i++) {
            System.out.println("(" + i + ") " + LowCommunicationReason.descriptions[i]);
        }
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();

        return  LowCommunicationReason.values()[selectedIndex] ;
    }

    private MeetingContribution askMeetingContribution(String name) {

        System.out.println() ;
        for (int i=0 ; i<MeetingContribution.descriptions.length ; i++) {
            System.out.println("(" + i + ") " + MeetingContribution.descriptions[i]);
        }
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();

        return  MeetingContribution.values()[selectedIndex] ;
    }

    private ContributionAmount askOverallContribution(String name) {

        System.out.println(ContributionAmount.question.replaceAll("NAME", name)) ;
        for (int i=0 ; i<ContributionAmount.descriptions.length ; i++) {
            System.out.println("(" + i + ") " + ContributionAmount.descriptions[i]);
        }
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();

        return  ContributionAmount.values()[selectedIndex] ;
    }

    private String askContributionDetails(String name) {
        System.out.println("What specific contributions has " + name + " made in the last week, and what did you think of them?");

        scanner = scanner.reset();

        return scanner.nextLine() ;
    }

    public Review createReview() {

        Group group = askGroup() ;

        Student reviewer = askGroupMember(group, "Who is writing this review?") ;
        Student reviewee = askGroupMember(group, "Who is this review about?") ;

        Review review = new Review(group.getId(), reviewee.getId(), reviewer.getId()) ;


        CommunicationAmount communicationAmount = null ;
        LowCommunicationReason lowCommunicationReason = null ;
        MeetingContribution meetingContribution = null ;
        ContributionAmount contributionAmount = null ;
        String contributionDetails = null ;

        communicationAmount = askCommunicationAmount(reviewee.getFirstName());

        if (communicationAmount == CommunicationAmount.low || communicationAmount == CommunicationAmount.none) {
            lowCommunicationReason = askLowCommunicationReason(reviewee.getFirstName());
        } else {
            meetingContribution = askMeetingContribution(reviewee.getFirstName()) ;
        }

        contributionAmount = askOverallContribution(reviewee.getFirstName()) ;

        if (contributionAmount == ContributionAmount.low || contributionAmount == ContributionAmount.medium || contributionAmount == ContributionAmount.high) {
            contributionDetails = askContributionDetails(reviewee.getFirstName()) ;
        }

        review.setDetails(communicationAmount, lowCommunicationReason, meetingContribution, contributionAmount, contributionDetails);

        return review ;
    }
}
