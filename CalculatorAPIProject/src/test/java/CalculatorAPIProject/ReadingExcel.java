package CalculatorAPIProject;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;

public class ReadingExcel {

	static String para1;
	static String para2;
	static String para3;

	public static Pojo getData(Iterator<Cell> cellIterator) throws IOException {

		Pojo pojo = new Pojo();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell.getColumnIndex() == 0) {
				para1 = cell.getStringCellValue();
			} else if (cell.getColumnIndex() == 1) {
				para2 = cell.getStringCellValue();
			} else if (cell.getColumnIndex() == 2) {
				para3 = cell.getStringCellValue();
			}
			pojo.setPara1(para1);
			pojo.setPara2(para2);
			pojo.setPara3(para3);

		}
		return pojo;
	}

}
