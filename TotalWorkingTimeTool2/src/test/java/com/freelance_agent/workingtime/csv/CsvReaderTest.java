package com.freelance_agent.workingtime.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.freelance_agent.workingtime.dto.WorkRecord;

class CsvReaderTest {

	@Test
	void testReadCSV_valid() {
		File testCSV = new File("valid.csv");
		
		testCSV.deleteOnExit();	
		
		CsvReader csvReader = new CsvReader();
		
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testCSV),"UTF-8"))){
			
			writer.write("社員番号,名前,日付,勤務時間");
			writer.newLine();
			writer.write("001,テスト太郎,20250210,180");
			
			writer.flush();			
			List<WorkRecord> records = new ArrayList<WorkRecord>();
			records = csvReader.readCSV("valid.csv");
			
			assertEquals("001", records.get(0).getEmployeeId());
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	@Test
	void testReadCSV_columnCheck_emloyeeID() {
		File testCSV = new File("invalid.csv");
		
		testCSV.deleteOnExit();	
		
		CsvReader csvReader = new CsvReader();
		
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testCSV),"UTF-8"))){
			
			writer.write("社員番号,名前,日付,勤務時間");
			writer.newLine();
			writer.write(",テスト太郎,20250210,180");
			
			writer.flush();			
			List<WorkRecord> records = new ArrayList<WorkRecord>();
			records = csvReader.readCSV("invalid.csv");
			
			assertEquals(0, records.size());
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

	@Test
	void testReadCSV_columnCheck_emloyeeName() {
		File testCSV = new File("invalid.csv");
		
		testCSV.deleteOnExit();	
		
		CsvReader csvReader = new CsvReader();
		
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testCSV),"UTF-8"))){
			
			writer.write("社員番号,名前,日付,勤務時間");
			writer.newLine();
			writer.write("001,,20250210,180");
			
			writer.flush();			
			List<WorkRecord> records = new ArrayList<WorkRecord>();
			records = csvReader.readCSV("invalid.csv");
			
			assertEquals(0, records.size());
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	@Test
	void testReadCSV_columnCheck_workDate() {
		File testCSV = new File("invalid.csv");
		
		testCSV.deleteOnExit();	
		
		CsvReader csvReader = new CsvReader();
		
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testCSV),"UTF-8"))){
			
			writer.write("社員番号,名前,日付,勤務時間");
			writer.newLine();
			writer.write("001,テスト太郎,,180");
			
			writer.flush();			
			List<WorkRecord> records = new ArrayList<WorkRecord>();
			records = csvReader.readCSV("invalid.csv");
			
			assertEquals(0, records.size());
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	@Test
	void testReadCSV_columnCheck_workingTime() {
		File testCSV = new File("invalid.csv");
		
		testCSV.deleteOnExit();	
		
		CsvReader csvReader = new CsvReader();
		
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testCSV),"UTF-8"))){
			
			writer.write("社員番号,名前,日付,勤務時間");
			writer.newLine();
			writer.write("001,テスト太郎,20250210,");
			
			writer.flush();			
			List<WorkRecord> records = new ArrayList<WorkRecord>();
			records = csvReader.readCSV("invalid.csv");
			
			assertEquals(0, records.size());
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	@Test
	void testReadCSV_validate() {
		File testCSV = new File("invalid.csv");
		
		testCSV.deleteOnExit();	
		
		CsvReader csvReader = new CsvReader();
		
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testCSV),"UTF-8"))){
			
			writer.write("社員番号,名前,日付,勤務時間");
			writer.newLine();
			writer.write("00,テスト太郎,20250210,180");
			
			writer.flush();			
			List<WorkRecord> records = new ArrayList<WorkRecord>();
			records = csvReader.readCSV("invalid.csv");
			
			assertEquals(0, records.size());
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	@Test
	void testReadCSV_culc() {
		File testCSV = new File("valid.csv");
		
		testCSV.deleteOnExit();	
		
		CsvReader csvReader = new CsvReader();
		
		try( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testCSV),"UTF-8"))){
			
			writer.write("社員番号,名前,日付,勤務時間");
			writer.newLine();
			writer.write("001,テスト太郎,20250210,180");
			writer.newLine();
			writer.write("001,テスト太郎,20250211,180");
			writer.newLine();
			writer.write("002,テスト次郎,20250210,180");
			writer.newLine();
			writer.write("002,テスト次郎,20250211,180");
			
			writer.flush();			
			List<WorkRecord> records = new ArrayList<WorkRecord>();
			records = csvReader.readCSV("valid.csv");
			
			assertEquals(360, records.get(0).getWorkingTime());
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
}
