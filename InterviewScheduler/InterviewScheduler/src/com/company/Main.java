package com.company;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Number;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

//uses jxl library (java excel library)
public class Main {
     static Workbook w;
     static Sheet sheet;
     static ArrayList<Student> studentList = new ArrayList<>();
     static ArrayList<Interviewer> interviewerList = new ArrayList<>();
     static ArrayList<SheetCell> availableSlots = new ArrayList<>();
     static Random rand = new Random();
    public static void main(String[] args) {
        openFile("C:\\Users\\cparker\\Downloads\\Copy_of_Programming_Interviews_18.xls");
        readStudentList(0, 1);
        readInterviewerList(0, 1);
        slots();
        try {
            write("C:\\Users\\cparker\\Downloads\\Copy_of_Programming_Interviews_18_2.xls");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    static void readStudentList(int column, int row) {
        sheet = w.getSheet(1);
        while (true) {
            //grab student
            Cell cell = sheet.getCell(column, row); //gets a cell
            if (cell.getContents().equals("")) { //if cell is empty, exit loop
                break;
            }
            //grab C2Exclude
            Cell cell2 = sheet.getCell(column + 1, row); //goes to columns to exclude
            String cell2Contents = cell2.getContents(); //stores contents of the cell into a string
            String[] columns = cell2Contents.split("\\s*,\\s"); //splits the comma-separated list into an array of strings
            ArrayList<Integer> c2Exclude = new ArrayList<>(); //makes a list of integers
            for (int i = 0; i < columns.length; i++) { //loop converts each number into an int
                try {
                    c2Exclude.add(Integer.parseInt(columns[i]) + 1);
                }
                catch (NumberFormatException ex) {
                    //add some code for telling user "bad format"
                }
            }
            //grab R2Exclude
            Cell cell3 = sheet.getCell(column + 2, row);
            String cell3Contents = cell3.getContents();
            String[] rows = cell3Contents.split("\\s*,\\s");
            ArrayList<Integer> r2Exclude = new ArrayList<>();
            for (int i = 0; i < rows.length; i++) {
                try {
                    r2Exclude.add(Integer.parseInt(rows[i]));
                }
                catch (NumberFormatException ex) {
                    //add some code for telling user "bad format"
                }
            }
            studentList.add(new Student(cell.getContents(), c2Exclude, r2Exclude));
            row++;
        }
    }

    static void readInterviewerList (int column, int row) {
        sheet = w.getSheet(0);
        while (true) {
            //grab interviewer
            Cell cell = sheet.getCell(column, row);
            if (cell.getContents().equals("")) {
                break;
            }
            Cell cell2 = sheet.getCell(column + 1, row);
            if (cell2.getContents().equals("")) {
                break;
            }
            Interviewer interviewer = new Interviewer(cell.getContents(), cell2.getContents());
            for (int i = 2; i < 9; i++) {
                availableSlots.add(new SheetCell(column + i, row, interviewer));
            }
            interviewerList.add(interviewer);
            row++;
        }
    }

    static void slots () {
        //loop twice
            //loops through student list
            //check if they're not already in that column or row or if they need to be excluded
                //randomly select a new slot
                //give up after 100 times fixme add this
                //if slot is valid, add slot to student and remove from available slot list
        for (int i = 0; i <= 1; i++) {
            for (int k = 0; k < studentList.size(); k++) {
                int cellIndex = rand.nextInt(availableSlots.size());
                while (studentList.get(k).checkCell(availableSlots.get(cellIndex)) == false) {
                    cellIndex = rand.nextInt(availableSlots.size());
                }
                availableSlots.remove(cellIndex);
            }
        }
    }

    static void write(String inputFile) throws IOException {
        try {
            //creates an excel file
            File inputWorkbook = new File(inputFile);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(inputWorkbook);
            WritableSheet writableSheet = writableWorkbook.createSheet("Sheet1", 0);
            for (int i = 0; i < studentList.size(); i++) {
                Student curStudent = studentList.get(i);
                for (int k = 0; k < curStudent.currentCells.size(); k++) {
                    SheetCell curCell = curStudent.currentCells.get(k);
                    writableSheet.addCell(new Label (curCell.getColumn(), curCell.getRow(), curStudent.name));
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void openFile(String inputFile) {
        File inputWorkbook = new File(inputFile);
        try {
            w = Workbook.getWorkbook(inputWorkbook);
        }
        catch (BiffException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}