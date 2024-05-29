package edu.ksalekk.setcalculator;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetCalculator {
    private final TreeSet<Integer> set1 = new TreeSet<>();
    private final TreeSet<Integer> set2 = new TreeSet<>();
    private String operation;
    private TreeSet<Integer> result;

    public void getUserInput() {
        System.out.println("Podaj działanie: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        extractDataFromString(input);
    }


    private void extractDataFromString(String input) {
        String inputRegex = "(\\[(\\s*\\d+\\s*,\\s*)*\\s*\\d+\\s*\\]\\s*)([*+-]\\s*)(\\[(\\s*\\d+\\s*,\\s*)*\\s*\\d+\\s*\\])";
        Pattern inputPattern = Pattern.compile(inputRegex);
        Matcher inputMatcher = inputPattern.matcher(input);

        if(!inputMatcher.find()) {
            throw new IllegalArgumentException("Invalid Input Data");
        }
        String set1String = inputMatcher.group(1);
        String operationString = inputMatcher.group(3);
        String set2String = inputMatcher.group(4);


        Pattern numberPattern = Pattern.compile("(\\d+)");
        Matcher numberMatcher = numberPattern.matcher(set1String);
        while(numberMatcher.find()) {
            set1.add(Integer.parseInt(numberMatcher.group()));
        }

        numberMatcher = numberPattern.matcher(set2String);
        while(numberMatcher.find()) {
            set2.add(Integer.parseInt(numberMatcher.group()));
        }


        Pattern operationPattern = Pattern.compile("[*+-]");
        Matcher operationMatcher = operationPattern.matcher(operationString);
        if(!operationMatcher.find()) {
            throw new RuntimeException("Cannot find operation char in regex");
        }
        operation = operationMatcher.group();
    }


    public void calculate() {
        result = new TreeSet<>(set1);

        switch (operation) {
            case "*" -> result.retainAll(set2);
            case "+" -> result.addAll(set2);
            case "-" -> result.removeAll(set2);
        }
    }


    public void showResult() {
        System.out.println(set1 + " " + operation + " " + set2 + " = " + result);
    }


    public void clearData() {
        set1.clear();
        set2.clear();
        result.clear();
    }


    public static void main(String[] args) {
        SetCalculator calculator = new SetCalculator();
        String exit = "1";
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                calculator.getUserInput();
                calculator.calculate();
                calculator.showResult();
                calculator.clearData();
            } catch(Exception e) {
                System.out.println("\nNieprawidłowy format danych wejściowych");
                System.out.println("Wymagany format:\t[dane_zbioru1] \t* | + | - \t[dane_zbioru2]");
                System.out.println("Oba zbiory muszą być zbiorami liczb nieujemnych\n");
                continue;
            }

            System.out.println("Wpisz 0, aby wyjść lub dowolny inny znak, aby spróbować ponownie");
            exit = scanner.next();
        } while(!exit.equals("0"));
    }
}
