package com.freelance_agent.workingtime.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WorkRecordTest {

	@Test
	void testWorkRecordStringStringStringString() {
		WorkRecord actual = new WorkRecord("001", "テスト太郎", "20250210", "180");
		
		assertEquals("001", actual.getEmployeeId());
		assertEquals("テスト太郎", actual.getEmployeeName());
		assertEquals("20250210", actual.getWorkDate());
		assertEquals("202502", actual.getWorkYearMonth());
		assertEquals(180, actual.getWorkingTime());
	}

	@Test
	void testEqualsObject() {
		WorkRecord record1 = new WorkRecord("001", "テスト太郎", "20250210", "180");
		WorkRecord record2 = new WorkRecord("001", "テスト太郎", "20250213", "480");
		
		assertTrue(record1.equals(record2));
		assertTrue(record2.equals(record1));
	}

	
	@Test
	void testSetWorkingTime() {
		WorkRecord actual = new WorkRecord("001", "テスト太郎", "20250210", "180");
		actual.setWorkingTime(480);
		
		assertEquals(480, actual.getWorkingTime());
	}

}
