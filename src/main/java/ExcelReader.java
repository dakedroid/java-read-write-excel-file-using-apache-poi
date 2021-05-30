
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by rajeevkumarsingh on 18/12/17.
 */
public class ExcelReader {

    public static final String SAMPLE_XLS_FILE_PATH = "./sample-xls-file.xls";
    public static final String SAMPLE_XLSX_FILE_PATH = "./estructura_29_may.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {

        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
         */
 /*
        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
        }

        // 2. Or you can use a for-each loop
        System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }
         */
        // 3. Or you can use a Java 8 forEach wih lambda
        System.out.println("IMPRIMIENDO HOJAS DE EL EXCEL DE ESTRUCTURA");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
         */
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        /*
        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }
         */
        // KEVIN DAVID 
        // ZENAIDA     QUEZADA        MERINO
        // MERINO          QUEZADA          ZENAIDA
        // QUEZADA  MERINO ZENAIDA
        String nombre = "ZENAIDA";
        String paterno = "QUEZADA";
        String materno = "MERINO";

        String foundNombre = "";
        String foundPaterno = "";
        String foundMaterno = "";

        boolean nombreInNOMBRES = false;
        boolean nombreInPATERNOS = false;
        boolean nombreInMATERNOS = false;

        boolean paternoInNOMBRES = false;
        boolean paternoInPATERNOS = false;
        boolean paternoInMATERNOS = false;

        boolean maternoInNOMBRES = false;
        boolean maternoInPATERNOS = false;
        boolean maternoInMATERNOS = false;

        boolean NOMBRES = false;
        boolean PATERNOS = false;
        boolean MATERNOS = false;

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nLoop\n");
        for (Row row : sheet) {

            for (Cell cell : row) {

                int cellColumnIndex = cell.getColumnIndex();
                // System.out.println("CELL NUM_COLMUNA: " + cellColumnIndex);

                String cellValue = dataFormatter.formatCellValue(cell);

                if (cellColumnIndex == 2) {

                    //System.out.print(cellValue + "\t");
                    if (cellValue.equals(nombre)) {

                        if (foundNombre.isEmpty()) {
                            nombreInNOMBRES = true;
                            foundNombre = cellValue;
                        }

                    } else {
                        if (foundNombre.isEmpty()) {
                            nombreInNOMBRES = false;

                            foundNombre = "";
                        }

                    }

                    // System.out.print(cellValue + "\t");
                    if (cellValue.equals(paterno)) {
                        if (foundPaterno.isEmpty()) {
                            paternoInNOMBRES = true;
                            foundPaterno = cellValue;
                        }
                    } else {
                        if (foundPaterno.isEmpty()) {
                            paternoInNOMBRES = false;

                            foundPaterno = "";
                        }

                    }

                    if (cellValue.equals(materno)) {
                        if (foundMaterno.isEmpty()) {
                            maternoInNOMBRES = true;
                            foundMaterno = cellValue;
                        }
                    } else {
                        if (foundMaterno.isEmpty()) {
                            paternoInNOMBRES = false;
                            foundMaterno = "";
                        }

                    }

                }

                if (cellColumnIndex == 3) {

                    //System.out.print(cellValue + "\t");
                    if (cellValue.equals(nombre)) {
                        if (foundNombre.isEmpty()) {
                            nombreInPATERNOS = true;
                            foundNombre = cellValue;
                        }
                    } else {

                        if (foundNombre.isEmpty()) {
                            nombreInPATERNOS = false;

                            foundNombre = "";
                        }

                    }

                    if (cellValue.equals(paterno)) {

                        if (foundPaterno.isEmpty()) {
                            paternoInPATERNOS = true;
                            foundPaterno = cellValue;
                        }
                    } else {

                        if (foundPaterno.isEmpty()) {
                            paternoInPATERNOS = false;

                            foundPaterno = "";
                        }
                    }

                    if (cellValue.equals(materno)) {
                        if (foundMaterno.isEmpty()) {
                            maternoInPATERNOS = true;
                            foundMaterno = cellValue;
                        }
                    } else {
                        if (foundMaterno.isEmpty()) {
                            maternoInPATERNOS = false;

                            foundMaterno = "";
                        }

                    }

                }

                if (cellColumnIndex == 4) {

                    //System.out.print(cellValue + "\t");
                    if (cellValue.equals(nombre)) {
                        if (foundNombre.isEmpty()) {
                            nombreInMATERNOS = true;
                            foundNombre = cellValue;
                        }
                    } else {
                        if (foundNombre.isEmpty()) {
                            nombreInMATERNOS = false;

                            foundNombre = "";
                        }
                    }

                    if (cellValue.equals(paterno)) {
                        if (foundPaterno.isEmpty()) {
                            paternoInMATERNOS = true;
                            foundPaterno = cellValue;
                        }
                    } else {
                        if (foundPaterno.isEmpty()) {
                            paternoInMATERNOS = false;

                            foundPaterno = "";
                        }
                    }

                    if (cellValue.equals(materno)) {
                        if (foundMaterno.isEmpty()) {
                            maternoInMATERNOS = true;
                            foundMaterno = cellValue;
                        }
                    } else {
                        if (foundMaterno.isEmpty()) {
                            maternoInMATERNOS = false;

                            foundMaterno = "";
                        }

                    }

                }

            }

            if (nombreInNOMBRES || nombreInPATERNOS || nombreInMATERNOS) {

                NOMBRES = true;
            }

            if (paternoInNOMBRES || paternoInPATERNOS || paternoInMATERNOS) {

                PATERNOS = true;
            }

            if (maternoInNOMBRES || maternoInPATERNOS || maternoInMATERNOS) {

                MATERNOS = true;
            }

            if (NOMBRES && PATERNOS && MATERNOS) {
                //System.out.print(cellValue + "\t");

                System.out.print(foundNombre + "\t" + foundPaterno + "\t" + foundMaterno);
                  System.out.println();

            }

            foundNombre = "";
            foundPaterno = "";
            foundMaterno = "";

            nombreInNOMBRES = false;
            nombreInPATERNOS = false;
            nombreInMATERNOS = false;

            paternoInNOMBRES = false;
            paternoInPATERNOS = false;
            paternoInMATERNOS = false;

            maternoInNOMBRES = false;
            maternoInPATERNOS = false;
            maternoInMATERNOS = false;

            NOMBRES = false;
            PATERNOS = false;
            MATERNOS = false;

       
        }

      
        // Closing the workbook
        workbook.close();
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }
}
