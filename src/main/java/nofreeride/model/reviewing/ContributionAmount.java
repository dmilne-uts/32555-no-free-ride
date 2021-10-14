package nofreeride.model.reviewing;

public enum ContributionAmount {

    none,
    low,
    medium,
    high,
    unknown;

    public static final String question = "Overall, what has NAME done to push the project forward in the last week?";

    public static final String[] descriptions = {
            "They haven't made any observable contributions in the last week.",
            "They have made some contributions in the last week, but they were low effort or low quality.",
            "They have made reasonable contributions in the last week.",
            "They have made strong contributions, that have really pushed the project forward.",
            "I don't know."
    };

    public String getDescription() {
        return descriptions[this.ordinal()];
    }
}
