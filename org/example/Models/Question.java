package advanced.org.example.Models;

public class Question {
    String question;
    String answer;

    public Question(String q, String a) {
        this.question = q;
        this.answer = a;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}