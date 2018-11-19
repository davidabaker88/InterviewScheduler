package com.company;

import jxl.Workbook;

import java.util.ArrayList;
import static com.company.Main.sheet;

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

    //true if it's a good cell
    //false means that student can't be placed in slot
    public boolean checkCell (SheetCell cellToCheck) {
        for (int i = 0; i < interviewerColumnsToExclude.size(); i++) {
            if (cellToCheck.getColumn() == interviewerColumnsToExclude.get(i)) {
                return false;
            }
        }
        for (int i = 0; i < interviewerRowsToExclude.size(); i++) {
            if (cellToCheck.getRow() == interviewerRowsToExclude.get(i)) {
                return false;
            }
        }
        for (int i = 0; i < currentCells.size(); i++) {
            if (cellToCheck.getColumn() == currentCells.get(i).getColumn()) {
                return false;
            }
            else if (cellToCheck.getRow() == currentCells.get(i).getRow()) {
                return false;
            }
        }
        currentCells.add(cellToCheck);
        return true;
    }
}