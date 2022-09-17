package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {

        if (!getFileExtension(csvFilePath).equals(".csv"))
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE,"Wrong file type");
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<CensusAnalyserCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CensusAnalyserCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CensusAnalyserCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<CensusAnalyserCSV> censusCSVIterator = csvToBean.iterator();;
            int namOfEateries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEateries++;
                CensusAnalyserCSV censusData = censusCSVIterator.next();
            }
            return namOfEateries;
        }catch (IOException exception) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND,"File not found!");
        }catch (Exception exception){
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE,"Wrong delimiter or header");
        }
    }

    public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
        System.out.println("FILE name : "+ csvFilePath);
        if (!getFileExtension(csvFilePath).equals(".csv"))
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NOT_A_CSV_TYPE,"Wrong file type");
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<StateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(StateCodeCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCodeCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCodeCSV> censusCSVIterator = csvToBean.iterator();
            Iterable<StateCodeCSV> csvIterable = () -> censusCSVIterator;
            return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND,"File not found!");
        } catch (Exception e){
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, "Wrong delimiter or header");
        }
    }

    private static String getFileExtension(String file) {
        String extension = "";
        try {
            if (file != null) {
                extension = file.substring(file.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }
}
