package model.reviewing;

public enum LowCommunicationReason {

    noNeed,
    difficultyWithMyself,
    difficultyWithGroupmate;

    public static final String question = "How come you haven't communicated much with NAME in the last week?" ;

    public static final String[] descriptions = {
            "There hasn't been much reason to meet.",
            "It's been difficult for me to find time for a meeting.",
            "It's been difficult to get them to come to a meeting",
    };

    public String getDescription() {

        return descriptions[this.ordinal()];
    }
}
