package com.java.glen;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
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

        try (
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                if(csvRecord.size() != 6){
                    //log invalid row
                    continue;
                }

                PersonRecord record = new PersonRecord(
                    csvRecord.get("firstname"),
                    csvRecord.get("lastname"),
                    csvRecord.get("date"),
                    csvRecord.get("division"),
                    csvRecord.get("points"),
                    csvRecord.get("summary")
                );
                records.add(record);
            }
        }  catch (IOException e) {
            System.err.println("Invalid/missing file");

            return new ArrayList<>();
            //log exception to file

        } catch (IllegalArgumentException e){
            System.err.println("File was found but is missing CSV headings");
            return new ArrayList<>();
            //log missing headings/invalid headings to file
        }

        return records;
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
        if(records.size() == 0)
            return "";

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
     * Sort a List of PersonRecords by Division (ascending) then Points (descending)
     * @param personRecords the List of PersonRecords to sort
     */
    public static void sortByDivisionThenPoints(List<PersonRecord> personRecords){
        //Sort by division ascending then points ascending.
        //good explanation here https://blog.jooq.org/2014/01/31/java-8-friday-goodies-lambdas-and-sorting/
        personRecords.sort(
                Comparator.comparing(PersonRecord::getDivision)
                        .thenComparing(PersonRecord::getPoints, Comparator.reverseOrder())
        );
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
