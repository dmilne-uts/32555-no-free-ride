package nofreeride.model.reviewing;

public enum CommunicationAmount {

    none,
    low,
    medium,
    high;

    public static final String question = "How much have you communicated with NAME about your group assignment in the last week?" ;

    public static final String[] descriptions = {
            "We haven't communicated at all about the assignment.",
            "We've had a few emails/text messages about the assignment, but nothing substantial.",
            "We've had at least one proper back-and-forth conversation about the assignment.",
            "We both attended a meeting outside of regular class, that was specifically about this assignment."
    };

    public String getDescription() {
        return descriptions[this.ordinal()];
    }
}

