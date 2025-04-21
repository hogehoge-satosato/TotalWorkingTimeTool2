package com.freelance_agent.workingtime.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.freelance_agent.workingtime.dto.WorkRecord;
import com.freelance_agent.workingtime.validate.Validator;

import lombok.extern.slf4j.Slf4j;

/**
 * CSV読み取りを行い、労働時間の合計を計算するクラス
 * property -
 * method   readCSV
 */
@Slf4j
public class CsvReader {
	
	/**
	 * CSV読み取りを行い、労働時間の合計の計算を行う
	 * <p>機能</P>
	 * <ul>
	 * 	<li>CSV読取</li>
	 * 	<li>バリデート</li>
	 * 	<li>合計の計算</li>
	 * 	<li>出力するレコードを作成</li>
	 * </ul>
	 * @param fileName String 読み取るCSVファイル名
	 * @return records {@link WorkRecord} CSV出力するレコード
	 */
	public List<WorkRecord> readCSV(String fileName){
		Validator valivator = new Validator();
		
		List<WorkRecord> workRecords = new ArrayList<>();
		int row = 0;
		int rrow = 0;
		//CSV
		try (CSVParser parser = CSVFormat.DEFAULT.builder().setIgnoreEmptyLines(false).get().parse(
	            new BufferedReader(new InputStreamReader(new FileInputStream( fileName), "UTF-8")))) {
	        boolean firstflg = true;
	        
			for (CSVRecord record : parser) {
				row++;
	        	if(firstflg){
	        		firstflg = false;
	        		continue;
	        	}
	        	// カラムチェック
	        	if(StringUtils.isEmpty(record.get(0)) ||
	        	   StringUtils.isEmpty(record.get(1)) ||
	        	   StringUtils.isEmpty(record.get(2)) ||
	        	   StringUtils.isEmpty(record.get(3)) 
	        	 ) {
	        		log.warn("カラムチェック ファイル名：{} 行：{}", fileName, row);
	        		continue;
	        	}
	        	// バリデート
	        	if(!valivator.validate(record, fileName, row)) {
	        		continue;
	        	}
	        	rrow++;
	        	// レコード登録・計算
	        	WorkRecord tmpRec = new WorkRecord(record.get(0), record.get(1), record.get(2), record.get(3));
	        	if(!workRecords.contains(tmpRec)) {
	        		workRecords.add(tmpRec);
	        	}else {
	        		for(WorkRecord r : workRecords) {
	        			if(r.equals(tmpRec)) {
	        				r.setWorkingTime(r.getWorkingTime() + tmpRec.getWorkingTime());
	        			}
	        		}
	        	}
	        }
			log.info("CSV読込処理完了　件数{}",rrow);
	    }catch(IOException e) {
	    	log.error(" 指定されたファイルの読み込みに失敗しました。ファイルパス：{}", fileName);
	    }
		return workRecords;
	}
}
