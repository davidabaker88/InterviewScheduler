package com.company;

/**
 * Created by dbaker on 4/29/2018.
 */
public class SheetCell {

    private int column;
    private int row;
    private Interviewer interviewer;

    public SheetCell(int Column, int Row, Interviewer interviewer){
        column = Column;
        row =  Row;
        this.interviewer = interviewer;
    }

    public SheetCell SwapSpots(SheetCell sheetToSwap){

        SheetCell cellToReturn = new SheetCell(column,row,interviewer);
        column = sheetToSwap.getColumn();
        row = sheetToSwap.getRow();
        interviewer = sheetToSwap.getInterviewer();
        return cellToReturn;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }
}
