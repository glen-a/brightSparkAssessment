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

    public PersonRecord(String firstName, String lastName, String date, String division, String points, String summary) {
        if (!DataManipulator.isNumber(division)) {
            //throw division unexpected format
        }
        if (!DataManipulator.isNumber(points)) {
            //throw points unexpected format
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.division = Integer.parseInt(division);
        this.points = Integer.parseInt(points);
        this.summary = summary;

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