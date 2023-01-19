package it.proactivity.utility;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParsingUtility {

    public static LocalDate parseStringIntoDate(String s) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate date = LocalDate.parse(s,formatter);
            return date;
        } catch (DateTimeParseException e){
            return null;
        }
    }
}
