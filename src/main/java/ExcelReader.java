
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
        
        
        foundPromovido(sheet, dataFormatter);

        
        // Closing the workbook
        workbook.close();
    }
    
    
    private static void foundPromovido (Sheet sheet, DataFormatter dataFormatter){
        
         String nombre = "ZENAIDA";
        String paterno = "QUEZADA";
        String materno = "MERINO";

        
        String foundNombre  = "";
        String foundPaterno = "";
        String foundMaterno = "";
        
        String foundUrbanizacion = "";
        String foundSeccion = "";
        
        
        boolean nombreFound = false;
        boolean paternoFound = false;
        boolean maternoFound = false;
      

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nLoop\n");
        for (Row row : sheet) {

            for (Cell cell : row) {

                int cellColumnIndex = cell.getColumnIndex();
                // System.out.println("CELL NUM_COLMUNA: " + cellColumnIndex);

                String cellValue = dataFormatter.formatCellValue(cell);

                if (cellColumnIndex == 2) {
                    foundNombre = cellValue;
                }

                if (cellColumnIndex == 3) {
                    foundPaterno = cellValue;
                }
                
                if (cellColumnIndex == 4) {
                   foundMaterno = cellValue;
                }
                
                if (cellColumnIndex == 6) {
                   foundUrbanizacion = cellValue;
                }
                
                if (cellColumnIndex == 8) {
                   foundSeccion = cellValue;
                }
            }
            
            if(nombre.equals(foundNombre) || nombre.equals(foundPaterno) || nombre.equals(foundMaterno)){
            
                nombreFound = true;
            
            }
            
            if(paterno.equals(foundNombre) || paterno.equals(foundPaterno) || paterno.equals(foundMaterno)){
            
                paternoFound = true;
            
            }
            
            if(materno.equals(foundNombre) || materno.equals(foundPaterno) || materno.equals(foundMaterno)){
            
                maternoFound = true;
            
            }
            
           
        

            if (nombreFound && paternoFound && maternoFound) {
                //System.out.print(cellValue + "\t");

                System.out.print("| "+foundNombre + "\t" + foundPaterno + "\t" + foundMaterno + " |\t| " + foundUrbanizacion + " |\t| " + foundSeccion + "|");
                System.out.println();

            }

            foundNombre = "";
            foundPaterno = "";
            foundMaterno = "";
            
            foundUrbanizacion = "";
            foundSeccion = "";
            
            nombreFound = false;
            paternoFound = false;
            maternoFound = false;
            
            
            
        }
    
    }


    private static void readCSV(){
    }
    
    
}
