package model;

import model.reviewing.CommunicationAmount;
import model.reviewing.ContributionAmount;
import model.reviewing.LowCommunicationReason;
import model.reviewing.MeetingContribution;

public class Review {

    private Integer id ;

    private CommunicationAmount communicationAmount ;
    private LowCommunicationReason lowCommunicationReason ;
    private MeetingContribution meetingContribution ;
    private ContributionAmount contributionAmount ;

    private String contributionDetails ;

    public Review(Integer id, CommunicationAmount communicationAmount, LowCommunicationReason lowCommunicationReason, MeetingContribution meetingContribution, ContributionAmount contributionAmount, String contributionDetails) {
        this.id = id;
        this.communicationAmount = communicationAmount;
        this.lowCommunicationReason = lowCommunicationReason;
        this.meetingContribution = meetingContribution;
        this.contributionAmount = contributionAmount;
        this.contributionDetails = contributionDetails;
    }

    public Integer getId() {
        return id;
    }

    public CommunicationAmount getCommunicationAmount() {
        return communicationAmount;
    }

    public LowCommunicationReason getLowCommunicationReason() {
        return lowCommunicationReason;
    }

    public MeetingContribution getMeetingContribution() {
        return meetingContribution;
    }

    public ContributionAmount getContributionAmount() {
        return contributionAmount;
    }

    public String getContributionDetails() {
        return contributionDetails;
    }

}
