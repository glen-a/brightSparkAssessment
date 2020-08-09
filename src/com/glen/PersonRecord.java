package com.glen;

public class PersonRecord {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date.trim();
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary.trim();
    }

    private String firstName;
    private String lastName;
    // date: String (format YYYY-MM-DD)
    private String date;
    private Integer division;
    private Integer points;
    private String summary;

    public PersonRecord(String[] attributes) {
        if (!isNumber(attributes[2])) {
            //throw division unexpected format
        }
        if (!isNumber(attributes[3])) {
            //throw points unexpected format
        }

        this.firstName = attributes[0];
        this.lastName = attributes[1];
        this.date = attributes[2];
        this.division = Integer.parseInt(attributes[3]);
        this.points = Integer.parseInt(attributes[4]);
        this.summary = attributes[5];

    }

    public String toString() {
        return "name : " + getFirstName() + " " + getLastName() +
                ", date: " + getDate()+
                ", division: " + getDivision()+
                ", points: " + getPoints()+
                ", summary: " + getSummary() ;
    }

    //should exist in some util class
    private Boolean isNumber(String str) {
        return (str.length() > 1 && str.matches("^[0-9]*$"));
    }
}