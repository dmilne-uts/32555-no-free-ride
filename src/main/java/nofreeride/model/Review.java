package nofreeride.model;

import nofreeride.model.reviewing.CommunicationAmount;
import nofreeride.model.reviewing.ContributionAmount;
import nofreeride.model.reviewing.LowCommunicationReason;
import nofreeride.model.reviewing.MeetingContribution;

import javax.persistence.*;

@Entity
public class Review {

    private static final double STRONG_SCORE = 0.25 ;
    private static final double MEDIUM_SCORE = 0.125 ;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id ;

    private Integer groupId ;
    private Integer reviewerId ;
    private Integer revieweeId ;

    private CommunicationAmount communicationAmount ;
    private LowCommunicationReason lowCommunicationReason ;
    private MeetingContribution meetingContribution ;
    private ContributionAmount contributionAmount ;

    private Double score ;

    private String contributionDetails ;

    public Review(Integer id, Integer groupId, Integer reviewerId, Integer revieweeId) {
        this.id = id ;
        this.groupId = groupId ;
        this.reviewerId = reviewerId ;
        this.revieweeId = revieweeId ;
    }

    public void setDetails(CommunicationAmount communicationAmount, LowCommunicationReason lowCommunicationReason, MeetingContribution meetingContribution, ContributionAmount contributionAmount, String contributionDetails) {
        this.communicationAmount = communicationAmount;
        this.lowCommunicationReason = lowCommunicationReason;
        this.meetingContribution = meetingContribution;
        this.contributionAmount = contributionAmount;
        this.contributionDetails = contributionDetails;

        this.score = calculateScore();
    }

    public Integer getId() {
        return id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public Integer getRevieweeId() {
        return revieweeId;
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

    public Double getScore() {
        return this.score ;
    }

    private double calculateScore() {

        double score = 0;

        switch (communicationAmount) {
            case none:
                score = score - STRONG_SCORE;
                break ;
            case low:
                score = score - MEDIUM_SCORE ;
                break ;
            case medium:
                score = score + MEDIUM_SCORE;
                break ;
            case high:
                score = score + STRONG_SCORE;

        }

        if (lowCommunicationReason != null) {
            switch (lowCommunicationReason) {
                case difficultyWithGroupmate:
                    score = score -  STRONG_SCORE ;
                    break;
            }
        }

        if (meetingContribution != null) {
            switch (meetingContribution) {
                case disruptive:
                    score = score - STRONG_SCORE ;
                    break ;
                case passive:
                    score = score - MEDIUM_SCORE ;
                    break ;
                case active:
                    score = score + MEDIUM_SCORE ;
                    break ;
                case leader:
                    score = score + STRONG_SCORE ;
                    break ;
            }
        }

        switch (contributionAmount) {
            case none:
                score = score - STRONG_SCORE ;
                break ;
            case low:
                score = score - MEDIUM_SCORE ;
                break ;
            case medium:
                score = score + MEDIUM_SCORE ;
                break ;
            case high:
                score = score + STRONG_SCORE ;
                break ;
        }

        //below is some optional math.
        //without this math, the scores range from -0.75 (terrible), to +0.75 (great).
        //the math makes it range from 0 (terrible) to 1 (great), which is what the case study specified.
        score = score * (1 / 0.75) ;
        score = (score + 1 ) / 2;

        return score ;
    }

}
