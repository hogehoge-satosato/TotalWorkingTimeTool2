package com.freelance_agent.workingtime.main;

import java.util.List;

import com.freelance_agent.workingtime.csv.CsvReader;
import com.freelance_agent.workingtime.csv.CsvWriter;
import com.freelance_agent.workingtime.dto.WorkRecord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("入力ファイル名を指定してください。");
			return;
		}
		
		log.info("勤務時間集計処理　処理開始");
		
		boolean append = (args.length > 1 && args[1].equals("A") ) ? true :false;
		
		
		String inputFile = args[0];
		String outputFile = "worktime_summary.csv";
		
		CsvReader reader = new CsvReader();
		CsvWriter writer = new CsvWriter();
		
		List<WorkRecord> records = reader.readCSV(inputFile);
		if(records.size() != 0) {
			writer.writeCsv(outputFile, records, append);
		}
		log.info("勤務時間集計処理　処理終了");
	}

}
