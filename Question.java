import java.util.List;

public class Question {
    String question;
    String answer;
    List<String> options;
    boolean isMCQ;

    // Constructor for direct questions
    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.isMCQ = false;
    }

    // Constructor for MCQ
    public Question(String question, List<String> options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.isMCQ = true;
    }

    public boolean isCorrect(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer.trim());
    }
}
