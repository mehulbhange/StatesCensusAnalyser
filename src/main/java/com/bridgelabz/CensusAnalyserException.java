package com.bridgelabz;

public class CensusAnalyserException extends Exception{

    enum ExceptionType{
        FILE_NOT_FOUND, NOT_A_CSV_TYPE, UNABLE_TO_PARSE
    }

    ExceptionType type;

    public CensusAnalyserException(ExceptionType type, String msg){
        super(msg);
        this.type =type;
    }

}
