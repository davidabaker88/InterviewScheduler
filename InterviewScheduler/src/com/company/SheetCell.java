package com.company;

/**
 * Created by dbaker on 4/29/2018.
 */
public class SheetCell {

    private int column;
    private int row;
    private String interviewerName;

    public SheetCell(int Column, int Row, String InterviewerName){
        column = Column;
        row =  Row;
        interviewerName = InterviewerName;
    }

    public SheetCell SwapSpots(SheetCell sheetToSwap){

        SheetCell cellToReturn = new SheetCell(column,row,interviewerName);
        column = sheetToSwap.getColumn();
        row = sheetToSwap.getRow();
        interviewerName = sheetToSwap.getInterviewerName();
        return cellToReturn;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getInterviewerName() {
        return interviewerName;
    }
}
