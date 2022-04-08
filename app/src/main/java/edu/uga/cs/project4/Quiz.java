package edu.uga.cs.project4;

/**
 * Quiz class, POJO
 */
public class Quiz {
    public int score = 0;
    public String date = "";
    public Question[] quiz;

    /**
     * Quiz constructor, creates quiz object from array of questions
     * @param q
     */
    public Quiz( Question[] q ) {
        quiz = q;
    }

    /**
     * Quiz constructor
     * @param sc
     * @param dt
     */
    public Quiz( int sc, String dt) {
        score = sc;
        date = dt;
    }

    /**
     * sets the date to given parameter
     * @param dt
     */
    public void setDate( String dt ) {
        date = dt;
    }

    /**
     * returns the score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * returns the date
     * @return date
     */
    public String getDate() {
        return date;
    }
}
