package com.glen;
import java.util.*;
import java.nio.file.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Main {

    public static void main(String[] args) {
        System.out.println("Import from file or clipboard?");
        //get user input f or c


        //file
        //get path
        String path = "C:\\Users\\Glen\\IdeaProjects\\brightSparkAssessment\\src\\Resources\\data.txt";

        List<PersonRecord> records = readRecordsFromFile(path);
        //clipboard

        for (PersonRecord p : records) {
            System.out.println(p);
        }
    }


    private static List<PersonRecord> readRecordsFromFile(String path){
        List<PersonRecord> records = new ArrayList<>();
        Path pathToFile = Paths.get(path);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            //if the first row is headings, ignore it.
            if(csvContainsHeader(line))
                line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");

                //if the row existed but had nothing then it was blank, ignore it
                if(attributes.length == 1){
                    System.out.println(("blank row"));
                    line = br.readLine();

                    continue;

                }
                //if we have more than 6 columns then the data isn't what we expected
                if(attributes.length != 6){
                    System.out.println(("invalid format")); //throw and stop here
                    line = br.readLine();
                    continue;
                }

                PersonRecord record = new PersonRecord(attributes);
                records.add(record);

                //grab the next line
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return records;
    }

    private static final String FIRSTNAME_HEADING = "firstname";
    private static final String LASTNAME_HEADING = "lastname";
    private static final String DATE_HEADING = "date";
    private static final String DIVISION_HEADING = "division";
    private static final String POINTS_HEADING = "firstname";
    private static final String SUMMARY_HEADING = "points";
    private static final String[] HEADINGS = {FIRSTNAME_HEADING, LASTNAME_HEADING, DATE_HEADING, DIVISION_HEADING, POINTS_HEADING, SUMMARY_HEADING};

    private static Boolean csvContainsHeader(String firstLine){
        boolean rowIsHeading = true;

        for (String heading  : HEADINGS) {
            rowIsHeading = firstLine.toLowerCase().contains(heading.toLowerCase()) && rowIsHeading;
        }

        return rowIsHeading;
    }
}
