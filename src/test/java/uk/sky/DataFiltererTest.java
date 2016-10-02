package uk.sky;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DataFiltererTest {

    @Before
    public void beforeTest() {
        reset();
    }

    @Test
    public void whenEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    @Test
    public void whenCorrectCountryIsNotLogs() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "NG").isEmpty());
    }

    @Test
    public void whenCountryWithResponseTimeNotAboveLimit() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 850).isEmpty());
    }

    @Test
    public void whenCountryWithResponseTimeAboveLimit() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 37).size() == 3);
    }


    @Test
    public void whenCorrectCountry() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "GB").size() == 1);
    }

    @Test
    public void whenCorrectResponseTimeIsAboveAverage() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")).size() == 3);
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }

    /***
     * Added to reset static class variables for test to avoid inconsistencies.
     */
    public static void reset() {
        DataFilterer.logExtracts = new ArrayList<>();
    }

}
