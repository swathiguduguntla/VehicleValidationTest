package com.car.qa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.car.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static String TEST_INPUT_FILE_PATH = System.getProperty("user.dir")
			+ "/src/main/java/com/car/qa/testdata/";
	public static String TEST_OUTPUT_FILE_PATH = System.getProperty("user.dir")
			+ "/src/main/java/com/car/qa/testdata/";

	static JavascriptExecutor js;

	public static ArrayList<String> readInputFile(String fileName) {

		ArrayList<String> mylist = new ArrayList<String>();
		try {
			// Regular expression for retrieving car number
			Pattern p = Pattern.compile("[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}");
			// Reading input file
			FileInputStream fis = new FileInputStream(TEST_INPUT_FILE_PATH+fileName);
			String content = IOUtils.toString(fis, "UTF-8");
			Matcher m = p.matcher(content);
			while (m.find()) {
				mylist.add(m.group().replace(" ", "").trim());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return mylist;
	}

	public static LinkedHashMap<String, HashMap<String, String>> readOutputFile(String outputFileName) {
		LinkedHashMap<String, HashMap<String, String>> mylist = new LinkedHashMap<String, HashMap<String, String>>();
		try {

			FileInputStream fstream = new FileInputStream(TEST_OUTPUT_FILE_PATH+outputFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			ArrayList<String> headerList = new ArrayList<String>();

			String strLine;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (strLine.contains("REGISTRATION")) {
					for (int i = 0; i < strLine.split(",").length; i++) {
						headerList.add(strLine.split(",")[i]); // Reading the Header from the File
					}
				} else {
					HashMap<String, String> values = new HashMap<String, String>();
					for (int i = 0; i < strLine.split(",").length; i++) {
						values.put(headerList.get(i), strLine.split(",")[i]); // Extracting Rows values with the header
																				// and assigning it to HashMap
					}
					mylist.put(strLine.split(",")[0], values);
				}
			}
			
			fstream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return mylist;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}
