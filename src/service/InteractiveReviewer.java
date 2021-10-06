package service;

import model.Review;
import model.reviewing.CommunicationAmount;
import model.reviewing.ContributionAmount;
import model.reviewing.LowCommunicationReason;
import model.reviewing.MeetingContribution;

import java.util.Scanner;

public class InteractiveReviewer {

    private Scanner scanner = new Scanner(System.in) ;

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

    public Review createReview(Integer id, String revieweeName) {

        CommunicationAmount communicationAmount = null ;
        LowCommunicationReason lowCommunicationReason = null ;
        MeetingContribution meetingContribution = null ;
        ContributionAmount contributionAmount = null ;
        String contributionDetails = null ;

        communicationAmount = askCommunicationAmount(revieweeName);

        if (communicationAmount == CommunicationAmount.low || communicationAmount == CommunicationAmount.none) {
            lowCommunicationReason = askLowCommunicationReason(revieweeName);
        } else {
            meetingContribution = askMeetingContribution(revieweeName) ;
        }

        contributionAmount = askOverallContribution(revieweeName) ;

        if (contributionAmount == ContributionAmount.low || contributionAmount == ContributionAmount.medium || contributionAmount == ContributionAmount.high) {
            contributionDetails = askContributionDetails(revieweeName) ;
        }

        Review review  = new Review(id, communicationAmount, lowCommunicationReason, meetingContribution, contributionAmount, contributionDetails) ;

        return review ;
    }
}
