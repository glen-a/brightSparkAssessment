package com.glen;

/**
 * Definition for a PersonRecord
 */
public class PersonRecord {

    private String firstName;
    private String lastName;
    // date: String (format YYYY-MM-DD)
    private String date;
    private Integer division;
    private Integer points;
    private String summary;

    public String getFullName() {
        return this.firstName.trim() + " " + this.lastName.trim();
    }

    public String getFullDetails(){
        return "In division " + this.division + " from " + this.date + " performing " + this.summary;
    }

    public Integer getDivision() {
        return division;
    }

    public Integer getPoints() {
        return points;
    }

    public PersonRecord(String[] attributes) {
        if (!DataManipulator.isNumber(attributes[2])) {
            //throw division unexpected format
        }
        if (!DataManipulator.isNumber(attributes[3])) {
            //throw points unexpected format
        }

        this.firstName = attributes[0];
        this.lastName = attributes[1];
        this.date = attributes[2];
        this.division = Integer.parseInt(attributes[3]);
        this.points = Integer.parseInt(attributes[4]);
        this.summary = attributes[5];

    }

    /**
     * Serialization class for dumping to YAML
     * A simplified version of PersonRecord
     */
    public class Simple{
        String name;
        String details;

        public void setName(String name) {
            this.name = name;
        }
        public void setDetails(String details) {
            this.details = details;
        }
        public String getName() {
            return this.name;
        }
        public String getDetails() {
            return this.details;
        }

        Simple(){
            this.name = getFullName();
            this.details = getFullDetails();
        }
    }

    public Simple serialize(){
        return new Simple();
    }
}