package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

public class ExcelMethods {

    private static final Logger log = LogManager.getLogger(ExcelMethods.class);

    // Base path
    public static final String BASE_PATH = System.getProperty("user.dir");

    // Excel files
    public static final String FILE_NAME =
            BASE_PATH + "/testdata/TestData.xlsx";
    public static final String MOBILE_FILE_NAME =
            BASE_PATH + "/testdata/MobileTestData.xlsx";

    /* =========================================================
       Public APIs
       ========================================================= */

    public static Object[][] getDataFromExcelTestData(String testName) throws Exception {
        return readExcelData(FILE_NAME, testName);
    }

    public static Object[][] getMobileDataFromExcelTestData(String testName) throws Exception {
        return readExcelData(MOBILE_FILE_NAME, testName);
    }

    /* =========================================================
       Core Excel Reader
       ========================================================= */

    private static Object[][] readExcelData(String filePath, String testName) throws Exception {

        Object[][] arrayExcelData;

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            /* ---------- DEBUG / DIAGNOSTICS ---------- */
            log.info("Excel path used: {}", filePath);
            log.info("Requested sheet name: [{}]", testName);

            int sheetCount = workbook.getNumberOfSheets();
            log.info("Sheets found in Excel ({}):", sheetCount);
            for (int i = 0; i < sheetCount; i++) {
                log.info(" - [{}]", workbook.getSheetName(i));
            }
            /* ---------------------------------------- */

            // Defensive trim to avoid hidden spaces in sheet name
            Sheet sheet = workbook.getSheet(testName.trim());

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet with the name '" + testName + "' not found in Excel file: " + filePath
                );
            }

            int totalNoOfCols = sheet.getRow(0).getLastCellNum();
            int totalNoOfRows = sheet.getLastRowNum(); // excluding header

            arrayExcelData = new Object[totalNoOfRows][totalNoOfCols];
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= totalNoOfRows; i++) { // start after header
                Row row = sheet.getRow(i);

                for (int j = 0; j < totalNoOfCols; j++) {
                    Cell cell = (row == null) ? null : row.getCell(j);
                    arrayExcelData[i - 1][j] = formatter.formatCellValue(cell);
                }
            }

        } catch (IOException e) {
            log.error("Error reading Excel file: {}", filePath, e);
            throw e;
        } catch (Exception e) {
            log.error("Error processing Excel data for test: {}", testName, e);
            throw e;
        }

        return arrayExcelData;
    }
}

