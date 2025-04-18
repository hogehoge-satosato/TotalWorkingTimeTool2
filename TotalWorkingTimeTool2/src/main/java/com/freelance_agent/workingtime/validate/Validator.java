package com.freelance_agent.workingtime.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.apache.commons.csv.CSVRecord;

import lombok.extern.slf4j.Slf4j;

/**
 * CSVレコードを特定項目に該当しているかチェックを行うクラス
 * property -
 * meshod   validate
*/
@Slf4j
public class Validator {
	/**
	 * CSVレコードを以下の項目に該当しているかチェックを行う
	 * <ul>
	 * 	<li>社員番号が3桁の数値</li>
	 * 	<li>日付がYYYYMMDD形式の正当な日付</li>
	 * 	<li>労働時間が3桁までの数値</li>
	 * </ul>
	 * 上記に違反した場合はログを出力し、 {@code false}を戻す。
	 * 
	 * @param record CSVRecord CSVファイルの1行のレコード
	 * @param fileName String CSVファイル名 ※ログに使用
	 * @param row int 検査対象の行の番号 ※ログに使用
	 * 
	 * @return {@code true} :すべての項目を通過した場合 {@code false} :チェックに違反した場合
	 */
	public boolean validate(CSVRecord record,String fileName, int row) {
		if(!record.get(0).matches("\\d{3}")) {
			log.warn("社員番号チェック ファイル名：{} 行：{}", fileName, row);
			return false;
		}
		if(!record.get(2).matches("\\d{8}")) {
			log.warn("日付桁数チェック ファイル名：{} 行：{}", fileName, row);
			return false;
		}
		try {
			LocalDate.parse(record.get(2),
				    DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT));
		} catch (Exception e) {
			log.warn("日付正当性チェック ファイル名：{} 行：{}", fileName, row);
			return false;
		}
		
		if(!record.get(3).matches("[1-9][0-9]{0,2}")) {
			log.warn("勤務時間桁数チェック ファイル名：{} 行：{}", fileName, row);
			return false;
		}
		return true;
	}
}
