package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/CensusData.csv";
    private static final String WRONG_CSV_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String SAMPLE_CSV_DELIMITERS = "./src/test/resources/IndiaStateCensusDataIncorrectDelimeter.csv";
    private static final String SAMPLE_CSV_HEADER = "./src/test/resources/IndiaStateCensusDataIncorrectHeader.csv";

    private static final String STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CSV_FILE_PATH = "./src/test/resources/Code.csv";
    private static final String WRONG_STATE_CSV_TYPE = "./src/test/resources/IndiaStateCode.txt";
    private static final String SAMPLE_STATE_CODE_DELIMITER = "./src/test/resources/IndiaStateCodeIncorrectDelimeter.csv";
    private static final String SAMPLE_STATE_CODE_HEADER = "./src/test/resources/IndiaStateCodeIncorrectHeader.csv";

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
    /*
    * Test cases for India State code csv file
    * */
    @Test
    public void given_IndianStateCodeCSVFile_ReturnsCorrectRecords() throws CensusAnalyserException {
        int numOfRecords = censusAnalyser.loadIndiaStateCode(STATE_CODE_CSV_FILE_PATH);
        System.out.println(numOfRecords);
        Assertions.assertEquals(37, numOfRecords);
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCode(WRONG_STATE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WhenWrongType_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCode(WRONG_STATE_CSV_TYPE);
        } catch (CensusAnalyserException e){
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WhenDelimiterIncorrect_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCode(SAMPLE_STATE_CODE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE , e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WhenHeaderIncorrect_ShouldThrowException() {
        try {
            censusAnalyser.loadIndiaStateCode(SAMPLE_STATE_CODE_HEADER);
        } catch (CensusAnalyserException e){
            Assertions.assertEquals(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }


}
