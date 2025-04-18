package com.freelance_agent.workingtime.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.freelance_agent.workingtime.dto.WorkRecord;

import lombok.extern.slf4j.Slf4j;


/**
 * CSVファイルを出力するクラス
 * property -
 * method   writeCSV
 */
@Slf4j
public class CsvWriter {
	/**
	 * CSVファイルを出力する
	 * @param fileName String 出力ファイル名
	 * @param records List of {@link WorkRecord} 出力するレコード
	 * @param append boolean 追記モード {@code true}:追記 {@code false}:新規
	 */
	public void writeCsv(String fileName, List<WorkRecord> records, boolean append) {
		try (CSVPrinter printer = new CSVPrinter(new FileWriter(fileName, append), CSVFormat.EXCEL)) {
		    printer.printRecord("社員番号","名前","対象年月","合計勤務時間");
		    for(WorkRecord record: records) {
		    	printer.printRecord(record.getEmployeeId(), record.getEmployeeName(), record.getWorkYearMonth(), record.getWorkingTime());
			}
		    log.info("CSV出力処理完了　出力数{}", records.size());
		} catch (IOException ex) {
			log.error("CSV出力処理に失敗しました。{}",ex.getMessage());
		}
	
		return;
	}
}
