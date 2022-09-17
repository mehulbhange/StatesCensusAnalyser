package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/CensusData.csv";
    private static final String WRONG_CSV_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String SAMPLE_CSV_DELIMITERS = "./src/test/resources/IndiaStateCensusDataIncorrectDelimeter.csv";
    private static final String SAMPLE_CSV_HEADER = "./src/test/resources/IndiaStateCensusDataIncorrectHeader.csv";

    StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() throws CensusAnalyserException {
        int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assertions.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    @Test
    public void  givenIndiaCensusData_WhenWrongType_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_TYPE);
        } catch (CensusAnalyserException e){
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenDelimiterIncorrect_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_DELIMITERS);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }
    @Test
    public void givenIndiaCensusData_WhenHeaderIncorrect_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaCensusData(SAMPLE_CSV_HEADER);
        } catch (CensusAnalyserException e){
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }

}
