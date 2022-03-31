package edu.uga.cs.project4;

public class Question {
    //public int qNumber;
    public boolean isCorrect = false;
    public String qAnswer;
    public String guess;

    public Question( String answer ) {
        // qNumber = number;
        qAnswer = answer;
    }

    public void gradeQuestion() {
        if( guess.equals(qAnswer)) {
            isCorrect = true;
        }
    }
}
