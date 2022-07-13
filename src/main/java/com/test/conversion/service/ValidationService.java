package com.test.conversion.service;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ValidationService {

    private static HashMap<String, Integer> mapping = new HashMap<>();
    private static HashMap<String, String> controlCharacterMappings = new HashMap<>();

    private static int getOnlyIndividualNum(String individualNum) {
        return Integer.valueOf(individualNum.substring(0, individualNum.length() - 1));
    }

    private static Object getOnlyControlCharacter(String individualNum) {
        return individualNum.substring((individualNum.length() - 1), individualNum.length());
    }

    private static String calculateControlCharacter(int rem) {
        return controlCharacterMappings.get(String.valueOf(rem));
    }

    private void addMappings() {
        mapping.put("-", 1900);
        mapping.put("+", 1800);
        mapping.put("A", 2000);
    }

    private void addControlCharacterMappings() {
        controlCharacterMappings.put("0", "0");
        controlCharacterMappings.put("1", "1");
        controlCharacterMappings.put("2", "2");
        controlCharacterMappings.put("3", "3");
        controlCharacterMappings.put("4", "4");
        controlCharacterMappings.put("5", "5");
        controlCharacterMappings.put("6", "6");
        controlCharacterMappings.put("7", "7");
        controlCharacterMappings.put("8", "8");
        controlCharacterMappings.put("9", "9");
        controlCharacterMappings.put("10", "A");
        controlCharacterMappings.put("11", "B");
        controlCharacterMappings.put("12", "C");
        controlCharacterMappings.put("13", "D");
        controlCharacterMappings.put("14", "E");
        controlCharacterMappings.put("15", "F");
        controlCharacterMappings.put("16", "H");
        controlCharacterMappings.put("17", "J");
        controlCharacterMappings.put("18", "K");
        controlCharacterMappings.put("19", "L");
        controlCharacterMappings.put("20", "M");
        controlCharacterMappings.put("21", "N");
        controlCharacterMappings.put("22", "P");
        controlCharacterMappings.put("23", "R");
        controlCharacterMappings.put("24", "S");
        controlCharacterMappings.put("25", "T");
        controlCharacterMappings.put("26", "U");
        controlCharacterMappings.put("27", "V");
        controlCharacterMappings.put("28", "W");
        controlCharacterMappings.put("29", "X");
        controlCharacterMappings.put("30", "Y");
    }

    public Boolean validate_ssn(String ssn) throws Exception {
        addMappings();
        addControlCharacterMappings();
        Pair splitSSNArr = findChar(ssn);
        String[] left = (String[]) splitSSNArr.getKey();
        String dateWithMonthAndYear = left[0];
        String individualNumWithControlCharacter = left[1];
        int individualNum = getOnlyIndividualNum(individualNumWithControlCharacter);
        String sign = (String) splitSSNArr.getValue();

        int date = Integer.valueOf(dateWithMonthAndYear) / 10000;
        int month = (Integer.valueOf(dateWithMonthAndYear) / 100) % 100;
        int year = mapping.get(sign) + (Integer.valueOf(dateWithMonthAndYear) % 100);

        log.info("Date of Birth of user is - " + date + ", month is - " + month + ", year is - " + year);

        log.info("validating individual number");
        Boolean checkIndividualValidate = validate_individual_num(individualNum);
        Object controlCharacterInput = getOnlyControlCharacter(individualNumWithControlCharacter);
        int allNumbersOfInput = Integer.valueOf(dateWithMonthAndYear + String.valueOf(individualNum));

        int rem = allNumbersOfInput % 31;
        Object calculatedControlCharacter = calculateControlCharacter(rem);

        if (controlCharacterInput.equals(calculatedControlCharacter) && checkIndividualValidate)
            return true;
        else
            return false;
    }


    private boolean validate_individual_num(int individual_num) {
        if (individual_num > 002 & individual_num < 899)
            return true;
        else
            return false;
    }

    private Pair<String[], String> findChar(String ssn) throws Exception {
        if (ssn.contains("-")) {
            String[] split = ssn.split("-");
            return new Pair(split, "-");
        } else if (ssn.contains("+")) {
            String[] split = ssn.split("+");
            return new Pair(split, "+");
        } else if (ssn.contains("A")) {
            String[] split = ssn.split("A");
            return new Pair(split, "A");
        } else {
            throw new Exception("Invalid special character found!!, Supported are - , + & A");
        }
    }
}
