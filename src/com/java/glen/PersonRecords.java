package com.java.glen;

import java.util.List;

/**
 * Wrapper for a list of PersonRecords
 * Makes dumping to YAML with snakeyaml in the specified format easier.
 */
public class PersonRecords {
    public PersonRecords(List<PersonRecord.Simple> records) {
        this.records = records;
    }

    public List<PersonRecord.Simple> getRecords() {
        return records;
    }

    public void setRecords(List<PersonRecord.Simple> records) {
        this.records = records;
    }

    List<PersonRecord.Simple> records;

}
