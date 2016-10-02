package uk.sky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class DataFilterer {

    public static List logExtracts = new ArrayList<>();

    public static Collection<LogExtract> filterByCountry(Reader source, String country) {

        List<LogExtract> logExtracts = extractAndModelEntries(source);

        List<LogExtract> result = logExtracts.stream()
                .filter(line -> line.getCountry_code().equals(country))
                .collect(Collectors.<LogExtract>toList());

        return result;
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        List<LogExtract> logExtracts = extractAndModelEntries(source);

        List<LogExtract> result = logExtracts.stream()
                .filter(line -> line.getCountry_code().equals(country))
                .filter(line -> LocalTime.ofSecondOfDay(Long.parseLong(line.getResponse_time())).isAfter(LocalTime.ofSecondOfDay(limit)))
                .collect(Collectors.<LogExtract>toList());

        return result;
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        List<LogExtract> logExtracts = extractAndModelEntries(source);

        //removes header line
        logExtracts.remove(0);

        double average = logExtracts
                .stream()
                .mapToLong(a -> Long.parseLong(a.getResponse_time()))
                        .average().getAsDouble();

        long finalAverage = (long) average;


        List<LogExtract> result = logExtracts.stream()
                .filter(line -> LocalTime.ofSecondOfDay(Long.parseLong(line.getResponse_time())).isAfter(LocalTime.ofSecondOfDay(finalAverage)))
                        .collect(Collectors.<LogExtract>toList());

        return result;
    }


    public static List<LogExtract> extractAndModelEntries(Reader source) {

        BufferedReader br = new BufferedReader(source);
        String strLine;
        try {
            if (source != null) {
                while ((strLine = br.readLine()) != null) {
                    String[] entry = strLine.split(",");
                    LogExtract logExtract = new LogExtract(entry[0], entry[1], entry[2]);
                    logExtracts.add(logExtract);
                }
            } else {
                return Collections.emptyList();
            }
            source.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return logExtracts;
    }


}

