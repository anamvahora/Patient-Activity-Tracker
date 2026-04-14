package its340_andreas_anamv;


class InterviewNode {
    String question;
    String dbField; // Added to map the answer to your SQL column
    InterviewNode yesBranch;
    InterviewNode noBranch;

    public InterviewNode(String question, String dbField) {
        this.question = question;
        this.dbField = dbField;
    }
}
