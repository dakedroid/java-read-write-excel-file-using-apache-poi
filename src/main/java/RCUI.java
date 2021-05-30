
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dakedroid
 */
public class RCUI extends javax.swing.JFrame {

    private static Workbook workbook;
    private static Sheet sheet;
    private static DataFormatter dataFormatter;

    private static ArrayList<PROMOVIDO> promovidosList = new ArrayList<PROMOVIDO>();

    private static String csvPATH = "./1042.csv";
    public static String SAMPLE_XLSX_FILE_PATH = "./estructura_29_may.xlsx";

    /**
     * Creates new form RCUI
     */
    public RCUI() throws IOException, InvalidFormatException {
        initComponents();
        setLocationRelativeTo(null);
        
        estructuraText.setText("");
        rcText.setText("");

    }

    public static void initEstructura() throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        dataFormatter = new DataFormatter();

        // Closing the workbook
    }

    private static void foundPromovidos(Sheet sheet, DataFormatter dataFormatter) {
        
        int seccionContador = 0;
       

        String foundNombre = "";
        String foundPaterno = "";
        String foundMaterno = "";

        String foundUrbanizacion = "";
        String foundSeccion = "";

        boolean nombreFound = false;
        boolean paternoFound = false;
        boolean maternoFound = false;
        boolean seccionFound = false;

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIniciando ...\n");
        for (Row row : sheet) {

            for (Cell cell : row) {

                int cellColumnIndex = cell.getColumnIndex();
                // System.out.println("CELL NUM_COLMUNA: " + cellColumnIndex);

                String cellValue = dataFormatter.formatCellValue(cell);

                if (cellColumnIndex == 2) {
                    foundNombre = cellValue.toUpperCase();
                }

                if (cellColumnIndex == 3) {
                    foundPaterno = cellValue.toUpperCase();
                }

                if (cellColumnIndex == 4) {
                    foundMaterno = cellValue.toUpperCase();
                }

                if (cellColumnIndex == 6) {
                    foundUrbanizacion = cellValue.toUpperCase();
                }

                if (cellColumnIndex == 8) {
                    foundSeccion = cellValue.toUpperCase();
                    
                    if (foundSeccion.equals(seccion.getText().toString())){
                        seccionContador ++;
                    }
                }
            }

            System.out.println("IMPRIMIENDO SIZE: " + promovidosList.size());

            //  JOptionPane.showMessageDialog(textCSV, promovidosList.size());
            for (int i = 0; i < promovidosList.size(); i++) {

                System.out.println("IMPRIMIENDO: " + promovidosList.get(i).getNombres());

                if (promovidosList.get(i).getNombres().equals(foundNombre) || promovidosList.get(i).getNombres().equals(foundPaterno) || promovidosList.get(i).getNombres().equals(foundMaterno)) {

                    nombreFound = true;

                }

                if (promovidosList.get(i).getPaterno().equals(foundNombre) || promovidosList.get(i).getPaterno().equals(foundPaterno) || promovidosList.get(i).getPaterno().equals(foundMaterno)) {

                    paternoFound = true;

                }

                if (promovidosList.get(i).getMaterno().equals(foundNombre) || promovidosList.get(i).getMaterno().equals(foundPaterno) || promovidosList.get(i).getMaterno().equals(foundMaterno)) {

                    maternoFound = true;

                }
                
                if ( foundSeccion.equals(promovidosList.get(i).getSeccion()) ){
                    seccionFound = true;
                }

            }

            if (nombreFound && paternoFound && maternoFound && seccionFound) {
                //System.out.print(cellValue + "\t");

                textCSV.append(" " + foundNombre + "\t" + foundPaterno + "\t" + foundMaterno + " \t\t " + foundUrbanizacion + " \t\t\t " + foundSeccion + "\n");

                System.out.print(" " + foundNombre + "\t" + foundPaterno + "\t" + foundMaterno + " \t\t " + foundUrbanizacion + " \t\t\t " + foundSeccion + "\n");

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
            seccionFound = false;

        }
        
        estructuraText.setText("Registros en la estructura de la seccion " + seccion.getText() + ": " +  String.valueOf(seccionContador));

    }

    public static void readCSV() throws IOException, InvalidFormatException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvPATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());) {

            String texto = textCSV.getText();

            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names

                String name = csvRecord.get("NOMBRES").toUpperCase();
                String paterno = csvRecord.get("APELLIDO PATERNO").toUpperCase();
                String materno = csvRecord.get("APELLIDO MATERNO").toUpperCase();

                String seccion = csvRecord.get("SECCION").toUpperCase();

                PROMOVIDO promovido = new PROMOVIDO(name, paterno, materno, seccion);

                promovidosList.add(promovido);

                textCSV.append(texto + name + " " + paterno + " " + materno + "\t\t\t" + seccion + "\n");

                //System.out.println(texto + name + " " + email + " " + phone + "\n");
            }
            
            rcText.setText("Promovidos por el RC en la seccion " + seccion.getText().toString() + ": " + String.valueOf(promovidosList.size() ) );

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textCSV = new javax.swing.JTextArea();
        seccion = new javax.swing.JTextField();
        estructuraText = new javax.swing.JLabel();
        rcText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(800, 800));
        setSize(new java.awt.Dimension(800, 800));

        jPanel2.setBackground(new java.awt.Color(255, 153, 102));

        jLabel1.setText("RC SOFTWARE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(465, 465, 465)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
        );

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        jButton2.setText("REALIZAR CONSULTA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textCSV.setColumns(20);
        textCSV.setRows(5);
        jScrollPane1.setViewportView(textCSV);

        seccion.setToolTipText("SECCION ELECTORAL");

        estructuraText.setText("REGISTROS EN LA ESTRUCTURA: 1011");

        rcText.setText("REGISTROS DEL RC: 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seccion, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(estructuraText))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1001, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(587, 587, 587)
                        .addComponent(rcText)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(seccion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(estructuraText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rcText)))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1))
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        try {
            //JOptionPane.showMessageDialog(rootPane, "click" + jFileChooser1.getSelectedFile());

            promovidosList.clear();
            textCSV.setText("");
            csvPATH = jFileChooser1.getSelectedFile().toString();

            readCSV();
        } catch (IOException ex) {
            Logger.getLogger(RCUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(RCUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            initEstructura();

            textCSV.setText("");
            foundPromovidos(sheet, dataFormatter);
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(RCUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(RCUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RCUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RCUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(RCUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidFormatException ex) {
                    Logger.getLogger(RCUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel estructuraText;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel rcText;
    public static javax.swing.JTextField seccion;
    public static javax.swing.JTextArea textCSV;
    // End of variables declaration//GEN-END:variables
}
