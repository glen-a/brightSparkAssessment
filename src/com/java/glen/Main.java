package com.java.glen;

import java.util.*;

/**
 * Questions/coments:
 * - Was my interpretation of sorting by division then points correct?
 * - How to handle points/divisions where more than 3 records are the (top) i.e. 5 records all of the same division/points
 * - The second set of example results has an incorrect record for "Zelma Ivatt", it lists their summary as "Defensive Duties"
 *   but the sample data has this listed as "Offensive Duties"
 * - Not sure what logging strategy to use - For now write to System.err if a breaking error or comment where a log may be appropriate 
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Please enter path of csv");
        Scanner scan = new Scanner(System.in);
        String path = scan.nextLine();

        //read from CSV
        List<PersonRecord> personRecords = DataManipulator.readRecordsFromFile(path);

        //sort the records
        DataManipulator.sortByDivisionThenPoints(personRecords);

        //dump the top 3 records to YAML
        String yaml =  DataManipulator.getYaml(personRecords);

        System.out.println(yaml);
    }
}
