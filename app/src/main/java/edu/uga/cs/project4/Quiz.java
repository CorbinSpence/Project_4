package edu.uga.cs.project4;

public class Quiz {
    public int score = 0;
    public int questionsAnswered = 0;
    public String date = "";
    public Question[] quiz;

    public Quiz( Question[] q ) {
        quiz = q;
    }

    public Quiz( int sc, String dt) {
        score = sc;
        date = dt;
    }

    public void incrementScore() {
        score++;
    }

    public void incrementQuestionsAnswered() {
        questionsAnswered++;
    }

    public void setDate( String dt ) {
        date = dt;
    }

    public int getScore() {
        return score;
    }

    public int getQuestionsAnswers() {
        return questionsAnswered;
    }

    public String getDate() {
        return date;
    }
}
