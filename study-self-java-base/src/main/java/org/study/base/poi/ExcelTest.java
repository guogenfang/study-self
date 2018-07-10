package org.study.base.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年3月20日
 */
public class ExcelTest {

	public static void xlxs() throws Exception {
		InputStream is = new FileInputStream("F://test//abc.xlsx");
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List<List<TmpClass>> list = new ArrayList<>();
		XSSFSheet sheet = xssfWorkbook.getSheetAt(0); // 获取文件的第一个sheet
		int numOfRows = sheet.getLastRowNum() + 1;
		for (int i = 1; i < numOfRows; i++) {
			List<TmpClass> tmpList = new ArrayList<>();
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					tmpList.add(new TmpClass(j + 1, cell.getStringCellValue()));
				}
			}
			list.add(tmpList);
		}
		System.out.println(list);
	}
	
	public static void xls() throws Exception {
		InputStream is = new FileInputStream("F://test//abc.xls");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0); // 获取文件的第一个sheet
		int numOfRows = sheet.getLastRowNum() + 1;
		for (int i = 1; i < numOfRows; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					System.out.println(cell.getStringCellValue());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		xlxs();
		// InputStream inp = new FileInputStream("G:\\翔安智慧法庭\\翔安法院\\名单.xls");
		// HSSFWorkbook workbook = new HSSFWorkbook(inp);
		// HSSFSheet sheet = workbook.getSheetAt(3); //获取文件的第一个sheet
		// Iterator<Row> rit = sheet.rowIterator();
		// Row row = null;
		// while ((row = rit.next()) != null) {
		// Iterator<Cell> iter = row.iterator();
		// Cell tmp = null;
		// while((tmp = iter.next()) != null) {
		// try {
		// System.out.println(tmp.getStringCellValue());
		// }
		// catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}
	
	static class TmpClass{
		
		private Integer index;
		
		private String value;
		
		
		public TmpClass(Integer index, String value) {
			super();
			this.index = index;
			this.value = value;
		}

		/**
		 * @return the index
		 */
		public Integer getIndex() {
			return index;
		}
		
		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
		
		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}
		
		/**
		 * @param index the index to set
		 */
		public void setIndex(Integer index) {
			this.index = index;
		}

		@Override
		public String toString() {
			return "TmpClass [index=" + index + ", value=" + value + "]";
		}
		
		
	}
	

}
