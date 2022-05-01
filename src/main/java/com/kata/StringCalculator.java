package com.kata;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class StringCalculator {
    private String delimiter = "[,\n]";
    private String numbersWithDelimiters;
    private List<String> negatives = new ArrayList<String>();

      int add(String input) {

            if (isInputEmpty(input)) {
                return 0;
            }
            parseDelimiterAndFindLineWithNumbersAndDelimiters(input);
            String[] inputSplittedByDelimiter = splitInputByDelimiter();
            return calculateSum(inputSplittedByDelimiter);
        }

    private boolean isInputEmpty(String input) {
        return input.length() == 0;
    }

    private void parseDelimiterAndFindLineWithNumbersAndDelimiters(String input) {
        if (hasCustomDelimiter(input)) {
            parseDelimiter(input);
            findLineWithNumbersAndDelimiters(input);
        } else {
            numbersWithDelimiters = input;
        }
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    private void findLineWithNumbersAndDelimiters(String input) {
        int firstNewLine = input.indexOf("\n");
        numbersWithDelimiters = input.substring(firstNewLine + 1);
    }

    private void parseDelimiter(String input) {
        delimiter = "";
        addDelimiters( input);
        delimiter = StringUtils.chop(delimiter);
    }

    private void addDelimiters(String input) {
        int startIndexDelimiter = 0;
        while (true) {
            startIndexDelimiter = input.indexOf("[", startIndexDelimiter) + 1;
            if (startIndexDelimiter == 0) {
                break;
            }
            int endIndexDelimiter = input.indexOf("]", startIndexDelimiter);
            delimiter += input.substring(startIndexDelimiter, endIndexDelimiter) + "|";
        }
    }

    private String[] splitInputByDelimiter() {
        return numbersWithDelimiters.split(delimiter);
    }

    private int calculateSum(String[] inputSplittedByDelimiter) {
        int result = 0;
        for (String token : inputSplittedByDelimiter) {
            result += addSingleToken(token);
        }
        throwExceptionIfNegativeTokensExist();
        return result;
    }

    private void throwExceptionIfNegativeTokensExist() {
        if (hasNegativeTokens()) {
            throw new IllegalArgumentException(String.format("negatives not allowed (%s)", StringUtils.join(negatives, ",")));
        }
    }

    private boolean hasNegativeTokens() {
        return negatives.size() > 0;
    }

    private int addSingleToken(String token) {
        Integer valueAsInteger = Integer.parseInt(token);
        if (isNegative(valueAsInteger)) {
            negatives.add(token);
        } else if (isInValidRange(valueAsInteger)) {
            return valueAsInteger;
        }
        return 0;
    }

    private boolean isNegative(Integer valueAsInteger) {
        return valueAsInteger < 0;
    }

    private boolean isInValidRange(Integer valueAsInteger) {
        return valueAsInteger < 1000;
    }

}
