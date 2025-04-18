package com.freelance_agent.workingtime.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * CSVレコードクラス
 * property 社員番号,名前,労働日付,対象年月,勤務時間
 * constructor 対象年月を労働日付から作成
 *             勤務時間の型を数値に変更
 * equals      労働日付と勤務時間を比較対象から外す
 */

@EqualsAndHashCode
@RequiredArgsConstructor
@Data
public class WorkRecord {
	private final String employeeId;
	private final String employeeName;
	@EqualsAndHashCode.Exclude
	private final String workDate;
	private String workYearMonth;
	@EqualsAndHashCode.Exclude
	private int workingTime;
	
	public WorkRecord(String employeeid, String employeename, String workdate, String workingtime) {
		this.employeeId = employeeid;
		this.employeeName = employeename;
		this.workDate = workdate;
		this.workYearMonth = workdate.substring(0, 6);
		this.workingTime = Integer.parseInt(workingtime);
	}
	
}
