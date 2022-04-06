package edu.uga.cs.project4;

import java.util.Random;

public class Question {
    private String country_name;        // country: question prompt
    private String country_continent;   // continent: the correct answer
    private String[] choices = new String[] {"1","2","3"};
    private static String[] continents = new String[] {
            "Asia",
            "Africa",
            "Europe",
            "Oceania",
            "North America",
            "South America"
    };

    public Question( String name, String continent ) {
        country_name = name;
        country_continent = continent;

        Random rand = new Random();

        // the answer choice that is correct
        int correct = rand.nextInt(3);
        choices[correct] = country_continent;

        // the answer choices that are incorrect
        int count = 0;
        while( count < choices.length ) {
            if( count == correct ) {
                count++;
            } else {
                String temp = continents[rand.nextInt(6)];

                if( isDuplicate( temp, choices ) == false ) {
                    choices[count] = temp;
                    count++;
                }
            }
        }

    }

    private boolean isDuplicate( String t, String[] c) {
        for( int i = 0; i < c.length; i++ ) {
            if( t.equals(c[i]) ) {
                return true;
            }
        }
        return false;
    }

    public String getCountry() {
        return country_name;
    }

    public String getAnswer() {
        return country_continent;
    }

    public String[] getChoices() {
        return choices;
    }
}
