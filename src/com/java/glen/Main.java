package com.java.glen;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        //DataManipulator dataManipulator = new DataManipulator();
        System.out.println("Please enter path of csv");
        //get path
        String path = "C:\\Users\\Glen\\IdeaProjects\\brightSparkAssessment\\src\\Resources\\data.txt";

        List<PersonRecord> personRecords = DataManipulator.readRecordsFromFile(path);

        //Sort by division ascending then points ascending.
        //good explanation here https://blog.jooq.org/2014/01/31/java-8-friday-goodies-lambdas-and-sorting/
        personRecords.sort(
                Comparator.comparing(PersonRecord::getDivision)
                        .thenComparing(PersonRecord::getPoints, Comparator.reverseOrder())
        );

        String yaml =  DataManipulator.getYaml(personRecords);
        System.out.println(yaml);
    }
}
