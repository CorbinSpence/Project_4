package edu.uga.cs.project4;

/**
 * POJO Country Class
 */
public class Country {
    public long id;
    public String name;
    public String continent;

    /**
     * Country constructor
     */
    public Country() {

    }

    /**
     * set name method
     * @param name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * set continent method
     * @param continent
     */
    public void setContinent( String continent ) {
        this.continent = continent;
    }

    /**
     * set ID method
     * @param id
     */
    public void setID(long id){this.id = id; }



}

