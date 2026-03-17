package framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelUtils {

    public static List<Map<String, String>> getSheetData(String filePath, String sheetName) {

        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet không tồn tại: " + sheetName);
            }

            // 🔥 Đọc header (dòng đầu)
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            List<String> headers = new ArrayList<>();
            for (int i = 0; i < colCount; i++) {
                headers.add(getCellValue(headerRow.getCell(i)));
            }

            // 🔥 Đọc từng dòng data
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();

                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    String value = getCellValue(cell);
                    rowData.put(headers.get(j), value);
                }

                dataList.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi đọc file Excel");
        }

        return dataList;
    }

    // 🔥 XỬ LÝ CELL VALUE (QUAN TRỌNG NHẤT)
    public static String getCellValue(Cell cell) {

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // tránh 1.0 → 1
                    double num = cell.getNumericCellValue();
                    if (num == (long) num) {
                        return String.valueOf((long) num);
                    } else {
                        return String.valueOf(num);
                    }
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return handleFormula(cell);

            case BLANK:
                return "";

            default:
                return "";
        }
    }

    // 🔥 XỬ LÝ FORMULA
    private static String handleFormula(Cell cell) {
        try {
            switch (cell.getCachedFormulaResultType()) {

                case STRING:
                    return cell.getStringCellValue();

                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());

                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                default:
                    return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
}