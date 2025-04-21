package com.freelance_agent.workingtime.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.freelance_agent.workingtime.dto.WorkRecord;

class CsvWriterTest {

	@Test
	void testWriteCsv_valid() {
		CsvWriter writer = new CsvWriter();
		String fileName = "worktime_summary.csv";
		File testCSV = new File(fileName);
		testCSV.deleteOnExit();	
		
		List<WorkRecord> records = new ArrayList<>();
		WorkRecord record = new WorkRecord("001", "テスト太郎", "20250210", "180");
		records.add(record);
		
		writer.writeCsv(fileName ,records, false);
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(testCSV),"UTF-8"))){
			reader.readLine();
			String actual = reader.readLine();
			
			assertEquals("001,テスト太郎,202502,180", actual);
			
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}

}
