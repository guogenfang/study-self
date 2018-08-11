package org.study.base.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.study.base.poi.ExcelTest.TmpClass;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月10日
 */
public class ExcelT01 {

	public static String buffer = "";
	public static void main(String[] args) throws Exception {
		xlxs();
		System.out.println(buffer);
	}

	/**[简要描述]：
	 * 用户: ggf
	 * 创建时间: 2018年7月10日
	 */
	public static void xlxs() {
		InputStream is = null;
		try {
			is = new FileInputStream("F://test//a.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFWorkbook xssfWorkbook = null;
		try {
			xssfWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = xssfWorkbook.getSheetAt(0); // 获取文件的第一个sheet
		int numOfRows = sheet.getLastRowNum() + 1;
		for (int i = 1; i < numOfRows; i++) {
			List<TmpClass> tmpList = new ArrayList<>();
			Row row = sheet.getRow(i);
			if (row != null) {
				try {
					String str = "INSERT INTO `ccr_court_code_ standard` VALUES (%s,'%s');";
					buffer += String.format(str, row.getCell(0).getNumericCellValue(), row.getCell(4).getStringCellValue());
				} catch (Exception e) {
				}
			}
		}
	}
}
