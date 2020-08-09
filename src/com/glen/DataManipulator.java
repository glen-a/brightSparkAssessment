package com.glen;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for manipulating data
 */
public class DataManipulator {

    /**
     * Read records from a CSV file at the path provided
     * @param path A path to the CSV to import
     * @return a list of PersonRecords
     */
    public static List<PersonRecord> readRecordsFromFile(String path){
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

    /**
     * Verify that the headings provided are what we expect
     * @param firstLine the first line of the CSV
     * @return true if headings were expected
     */
    private static Boolean csvContainsHeader(String firstLine){
        boolean rowIsHeading = true;

        for (String heading  : HEADINGS) {
            rowIsHeading = firstLine.toLowerCase().contains(heading.toLowerCase()) && rowIsHeading;
        }

        return rowIsHeading;
    }

    /**
     * Dump a list of PersonRecords in the YAML format
     * records:
     * - name: <firstname> <lastname>
     *   details: In division <division> from <date> performing <summary>
     *
     * @param records the records to dump
     * @return A YAML string representing the data provided
     */
    public static String getYaml(List<PersonRecord> records){
        //exclude class names from yaml dump
        Representer representer = new Representer();
        representer.addClassTag(PersonRecord.Simple.class, Tag.MAP);
        representer.addClassTag(PersonRecords.class, Tag.MAP);

        //remove curly braces from yaml dump and other options
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yamlProcessor = new Yaml(representer, options);

        //Grab the top 3 items from our list, grab the serialize object from these and cast to something snakeyaml can work with
        //wrap in PersonRecords() for specified yaml output
        PersonRecords rec = new PersonRecords(records.stream().limit(3).map(PersonRecord::serialize).collect(Collectors.toList()));
        String yaml = yamlProcessor.dump(rec);

        return yaml;
    }

    /**
     * Verify if the provided string is entirely digits
     * @param str the string to check
     * @return True if the string was digits, false if contained something other than 0-9
     */
    public static Boolean isNumber(String str) {
        return (str.length() > 1 && str.matches("^[0-9]*$"));
    }
}
