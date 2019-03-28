package CalculatorAPIProject;

import org.testng.Reporter;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import io.restassured.RestAssured;

public class AppTest {

	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static String para1;
	static String para2;
	static String para3;
	static String Response;
	static String pathLink;
	static String URL;
	static String currentDir;

	@Test(priority = -1)
	public static void getdata() throws Exception {
		
		Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"//config.properties");
        prop.load(fileInputStream);
        fileInputStream.close();
        URL = prop.getProperty("baseURI");
        
        currentDir=(System.getProperty("user.dir")+File.separator+"library//CalculatorTestDetails.xls");
		File source = new File(currentDir);
		FileInputStream fis = new FileInputStream(source);
		workbook = new HSSFWorkbook(fis);
		int sheetnumber = 0;
		sheet = workbook.getSheetAt(sheetnumber);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row rows = rowIterator.next();
			Iterator<Cell> cellIterator = rows.cellIterator();
			Pojo pojo  = ReadingExcel.getData(cellIterator);
			RestAssured.baseURI = URL;
			Response = RestAssured.given().param("operation", pojo.getPara1()).param("operand1", pojo.getPara2()).param("operand2", pojo.getPara3())
					.when().get().then().extract().asString();
			Reporter.log(Response, true);

		}

		workbook.close();
	}
}
