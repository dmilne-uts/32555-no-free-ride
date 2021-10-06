package model.reviewing;

public enum MeetingContribution {

    disruptive,
    passive,
    active,
    leader;

    public static final String question = "How well did NAME contribute to the conversation or meeting?" ;

    public static final String[] descriptions = {
            "They were unhelpful and disruptive.",
            "They were mostly quiet and passive.",
            "They were active participants.",
            "They led it.",
    };

    public String getDescription() {
        return descriptions[this.ordinal()];
    }
}
