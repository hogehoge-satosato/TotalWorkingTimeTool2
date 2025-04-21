package com.freelance_agent.workingtime.validate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

class ValidatorTest {

	@Test
	void testValidate_valid() {
		Validator validator = new Validator();
		
		CSVRecord record = mock(CSVRecord.class);
		when(record.get(0)).thenReturn("001");
		when(record.get(1)).thenReturn("テスト太郎");
		when(record.get(2)).thenReturn("20250210");
		when(record.get(3)).thenReturn("180");
		
		assertTrue(validator.validate(record, "valid.csv", 1));
	}
	
	@Test
	void testValidate_checkEmployeeID() {
		Validator validator = new Validator();
		
		CSVRecord record = mock(CSVRecord.class);
		when(record.get(0)).thenReturn("01");
		when(record.get(1)).thenReturn("テスト太郎");
		when(record.get(2)).thenReturn("20250210");
		when(record.get(3)).thenReturn("180");
		
		assertFalse(validator.validate(record, "invalid.csv", 1));
	}
	
	@Test
	void testValidate_checkDateFormet() {
		Validator validator = new Validator();
		
		CSVRecord record = mock(CSVRecord.class);
		when(record.get(0)).thenReturn("001");
		when(record.get(1)).thenReturn("テスト太郎");
		when(record.get(2)).thenReturn("202502100");
		when(record.get(3)).thenReturn("180");
		
		assertFalse(validator.validate(record, "invalid.csv", 1));
	}
	
	@Test
	void testValidate_checkDateValid() {
		Validator validator = new Validator();
		
		CSVRecord record = mock(CSVRecord.class);
		when(record.get(0)).thenReturn("001");
		when(record.get(1)).thenReturn("テスト太郎");
		when(record.get(2)).thenReturn("20250229");
		when(record.get(3)).thenReturn("180");
		
		assertFalse(validator.validate(record, "invalid.csv", 1));
	}
	
	@Test
	void testValidate_checkWorkingTime() {
		Validator validator = new Validator();
		
		CSVRecord record = mock(CSVRecord.class);
		when(record.get(0)).thenReturn("001");
		when(record.get(1)).thenReturn("テスト太郎");
		when(record.get(2)).thenReturn("20250210");
		when(record.get(3)).thenReturn("1800");
		
		assertFalse(validator.validate(record, "invalid.csv", 1));
	}
}
