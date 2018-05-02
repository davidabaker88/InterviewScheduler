package com.company;

import java.util.ArrayList;

/**
 * Created by dbaker on 4/29/2018.
 */
public class Student {
    String name;
    ArrayList<Integer> interviewerColumnsToExclude;
    ArrayList<Integer> interviewerRowsToExclude;
    ArrayList <SheetCell> currentCells;

    public Student(String Name, ArrayList<Integer> InterviewerColumnsToExclude,
                   ArrayList<Integer> InterviewerRowsToExclude){
        name = Name;
        interviewerColumnsToExclude = InterviewerColumnsToExclude;
        interviewerRowsToExclude = InterviewerRowsToExclude;
        currentCells = new ArrayList<>();
    }
    //will check to make sure it can swap spots will return -1,-1, "null" if it can not
    public SheetCell FindCellToSwap(SheetCell sheetToSwap){

        SheetCell cellToReturn;

        //check if cell is in exclude lists.
        for(int i=0; i<interviewerRowsToExclude.size(); i++){
            if (interviewerRowsToExclude.get(i)==sheetToSwap.getRow()){
                return new SheetCell(-1,-1,"null");
            }
        }
        for(int i=0; i<interviewerColumnsToExclude.size(); i++){
            if (interviewerColumnsToExclude.get(i)==sheetToSwap.getColumn()){
                return new SheetCell(-1,-1,"null");
            }
        }

        //check if it will conflict

        return cellToReturn;
    }

}
