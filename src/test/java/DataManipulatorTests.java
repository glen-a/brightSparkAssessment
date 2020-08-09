package test.java;
import com.java.glen.DataManipulator;
import com.java.glen.PersonRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

//        //String path = "C:\Users\Glen\IdeaProjects\brightSparkAssessment\src\Resources\data.txt";

public class DataManipulatorTests  {

    private static final String PATH_TO_TEST_DATA = "out\\production\\brightSparkAssessment\\test\\resources\\";

    @Test
    public void verifyIsNumberAllDigits() {
        Boolean result = DataManipulator.isNumber("1234");
        assertTrue(result);
    }

    @Test
    public void verifyIsNumberAllDigitsLeadingZero() {
        Boolean result = DataManipulator.isNumber("01234");
        assertTrue(result);
    }

    @Test
    public void verifyIsNumberMixAlphaNumeric() {
        Boolean result = DataManipulator.isNumber("12d34");
        assertFalse(result);
    }

    @Test
    public void verifyIsNumberAllLetters() {
        Boolean result = DataManipulator.isNumber("asdg");
        assertFalse(result);
    }

    @Test
    public void verifyIsNumberEmptyString() {
        Boolean result = DataManipulator.isNumber("");
        assertFalse(result);
    }

    @Test
    public void canReadCSVWithHeadingsCSV() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "validWithHeading.csv");

        assertEquals(10, result.size());
    }

    @Test
    public void canReadCSVWithHeadingsTxt() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "validWithHeading.txt");

        assertEquals(10, result.size());
    }

    @Test
    public void canNotReadCSVNoHeading() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "validNoHeading.txt");

        assertEquals(0, result.size());
    }

    @Test
    public void canReadCSVWithHeadingLeadingSpaces() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "validWithHEadingLeadingSpaces.txt");

        assertEquals(10, result.size());
    }

    @Test
    public void canNotReadCSVNotEnoughColumns() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "invalidLessColumns.txt");

        assertEquals(0, result.size());
    }

    @Test
    public void canNotReadCSVTooManyColumns() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "invalidMoreColumns.txt");

        assertEquals(0, result.size());
    }

    @Test
    public void canPartlyReadCSVMissingData() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "invalidWonkyColumns.txt");

        assertEquals(5, result.size());
        //verify logs that 5 were not as expected
    }

    @Test
    public void canNotReadMissingCSV() {
        List<PersonRecord> result = DataManipulator.readRecordsFromFile( PATH_TO_TEST_DATA + "asdfasd.txt");

        assertEquals(0, result.size());
        //verify logs that 5 were not as expected
    }

    @Test
    public void canGetYAMLForPersonRecords() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();
        records.add(new PersonRecord("glen", "wiltshire", "2017-09-15", "1", "90", "being awesome"));
        records.add(new PersonRecord("someone", "else", "2019-02-15", "4", "2", "being lame"));
        records.add(new PersonRecord("another", "person", "2012-03-15", "3", "56", "blah blah"));

        String result = DataManipulator.getYaml((records));

        assertEquals(3, count(result, "name"));
    }

    @Test
    public void canGetYAMLContainsRoot() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();
        records.add(new PersonRecord("glen", "wiltshire", "2017-09-15", "1", "90", "being awesome"));
        records.add(new PersonRecord("someone", "else", "2019-02-15", "4", "2", "being lame"));
        records.add(new PersonRecord("another", "person", "2012-03-15", "3", "56", "blah blah"));

        String result = DataManipulator.getYaml((records));

        assertEquals(1, count(result, "records:"));
    }

    @Test
    public void canGetYAMLNoSurroundingBrackets() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();
        records.add(new PersonRecord("glen", "wiltshire", "2017-09-15", "1", "90", "being awesome"));
        records.add(new PersonRecord("someone", "else", "2019-02-15", "4", "2", "being lame"));
        records.add(new PersonRecord("another", "person", "2012-03-15", "3", "56", "blah blah"));

        String result = DataManipulator.getYaml((records));

        assertEquals(0, count(result, "{"));
        assertEquals(0, count(result, "}"));
    }

    @Test
    public void canGetYAMLNoClassEntityInformation() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();
        records.add(new PersonRecord("glen", "wiltshire", "2017-09-15", "1", "90", "being awesome"));
        records.add(new PersonRecord("someone", "else", "2019-02-15", "4", "2", "being lame"));
        records.add(new PersonRecord("another", "person", "2012-03-15", "3", "56", "blah blah"));

        String result = DataManipulator.getYaml((records));

        assertEquals(0, count(result, "PersonRecord"));
        assertEquals(0, count(result, "Simple"));
    }
    @Test
    public void canGetYAMLOneRecord() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();
        records.add(new PersonRecord("glen", "wiltshire", "2017-09-15", "1", "90", "being awesome"));

        String result = DataManipulator.getYaml((records));

        assertEquals(1, count(result, "name"));
        assertEquals(1, count(result, "glen"));
        assertEquals(1, count(result, "records"));

    }
    @Test
    public void canGetYAMLNoRecord() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();

        String result = DataManipulator.getYaml((records));

        assertEquals(0, count(result, "name"));
        assertEquals(0, count(result, "records"));

    }

    @Test
    public void canGetSortPersonRecords() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();
        PersonRecord first = new PersonRecord("glen", "wiltshire", "2017-09-15", "1", "90", "being awesome");
        PersonRecord second = new PersonRecord("someone", "else", "2019-02-15", "4", "2", "being lame");
        PersonRecord third = new PersonRecord("more", "people", "2012-03-15", "3", "56", "blah blah");
        PersonRecord fourth = new PersonRecord("in", "this", "2012-03-15", "1", "45", "blah blah");
        PersonRecord fifth =  new PersonRecord("with", "random", "2012-03-15", "2", "24", "blah blah");
        PersonRecord sixth = new PersonRecord("unique", "names", "2012-03-15", "3", "62", "blah blah");
        PersonRecord seventh = new PersonRecord("to", "filter", "2012-03-15", "5", "58", "blah blah");
        PersonRecord eighth = new PersonRecord("on", "unicorns", "2012-03-15", "9", "83", "blah blah");
        records.add(first);
        records.add(second);
        records.add(third);
        records.add(fourth);
        records.add(fifth);
        records.add(sixth);
        records.add(seventh);
        records.add(eighth);

        DataManipulator.sortByDivisionThenPoints(records);

        assertEquals(first, records.get(0));
        assertEquals(fourth, records.get(1));
        assertEquals(fifth, records.get(2));
    }

    @Test
    public void canGetSortPersonRecordsOneRecord() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();
        PersonRecord first = new PersonRecord("glen", "wiltshire", "2017-09-15", "1", "90", "being awesome");
        records.add(first);

        DataManipulator.sortByDivisionThenPoints(records);

        assertEquals(first, records.get(0));
        assertEquals(1, records.size());
    }

    @Test
    public void canGetSortPersonRecordsNoRecords() {
        List<PersonRecord> records = new ArrayList<PersonRecord>();

        DataManipulator.sortByDivisionThenPoints(records);

        assertEquals(0, records.size());
    }


    public static int count(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }
}
