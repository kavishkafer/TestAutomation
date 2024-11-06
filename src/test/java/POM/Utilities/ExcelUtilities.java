package POM.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
  public static Object[][] getExcelData(String filePath, String sheetName) {
    List<String> data = new ArrayList<>();

    try (FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheet(sheetName);
      int rowCount = sheet.getPhysicalNumberOfRows();

      // Skip the header row by starting from row index 1
      for (int i = 1; i < rowCount; i++) {
        Row row = sheet.getRow(i);
        if (row != null && row.getCell(0) != null) {
          data.add(row.getCell(0).getStringCellValue());
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    Object[][] dataArray = new Object[data.size()][1];
    for (int i = 0; i < data.size(); i++) {
      dataArray[i][0] = data.get(i);
    }
    return dataArray;
  }

}
