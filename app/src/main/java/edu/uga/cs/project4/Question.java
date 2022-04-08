package edu.uga.cs.project4;

import java.util.Random;

/**
 * Question class
 * contains the variables for a question object
 * and creates 2 random choices and 1 correct
 */
public class Question {
    private String country_name;        // country: question prompt
    private String country_continent;   // continent: the correct answer
    private String[] choices = new String[] {"1","2","3"};  // the countries in the choices
    private static String[] continents = new String[] {
            "Asia",
            "Africa",
            "Europe",
            "Oceania",
            "North America",
            "South America"
    };

    /**
     * Question constructor, creating a question object with entered parameters
     * will initialize name, continent, and choices array
     * @param name
     * @param continent
     */
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

    /**
     * Checks if a String occurs within a given array.
     * @param t
     * @param c
     * @return
     */
    private boolean isDuplicate( String t, String[] c) {
        for( int i = 0; i < c.length; i++ ) {
            if( t.equals(c[i]) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets country method
     * @return country_name
     */
    public String getCountry() {
        return country_name;
    }

    /**
     * get answer method, answer being the continent that the country
     * is a part of
     * @return country_continent
     */
    public String getAnswer() {
        return country_continent;
    }

    /**
     * get choices method, returns an array that contains the choices
     * for a given question object.
     * @return choices array
     */
    public String[] getChoices() {
        return choices;
    }
}
