# TotalWorkingTimeTool2
20250416課題　勤務時間集計ツール

## USAGE
1. TotalWorkingTimeTool2-1.0.jarをダウンロード
1. コマンド実行  
`java -jar TotalWorkingTimeTool2-1.0.jar CSVファイルパス N`

## 課題内容
勤務時間集計プログラム
■1. 概要
社員の勤務時間を記録したCSVファイルをもとに、社員番号および対象年月ごとの勤務時間合計を集計し、CSV形式で出力する。
また、入力ファイルに対して各種バリデーションを行い、不正データはログに出力し、処理対象外とする。

■2. 入力仕様
- ファイル形式：CSV（カンマ区切り）
- ファイル名：任意（コマンドライン引数で指定）
- 文字コード：UTF-8
- ヘッダ：あり
- カラム順：
  - 社員番号
  - 名前
  - 日付（yyyyMMdd）
  - 勤務時間（分）

※例：
社員番号,名前,日付,勤務時間
001,テスト太郎,20250210,180
001,テスト太郎,20250211,198
001,テスト太郎,20250212,257
002,テスト次郎,20250210,177

■3. 出力仕様
- ファイル形式：CSV
- 出力ファイル名：worktime_summary.csv
- 文字コード：UTF-8
- ヘッダ：あり
- カラム順：
  - 社員番号
  - 名前
  - 対象年月（例：202502）
  - 合計勤務時間（分）

※例：
社員番号,名前,対象年月,合計勤務時間
001,テスト太郎,202502,635
002,テスト次郎,202502,177

■4. 処理概要
1. 入力CSVを読み込み、1レコードずつバリデーションを実施。
2. エラーのないレコードのみを対象に、社員番号と年月単位で勤務時間を集計。
3. 集計結果を出力用CSVに書き出す。
4. バリデーションエラーのあるレコードはスキップし、エラーログに出力。

■5. バリデーション仕様
以下の条件に当てはまるレコードは、エラーとして処理対象外とする：
- 社員番号が3桁の数値でない場合（例：abc → エラー）
- 日付が8桁の数値でない場合（例：20251340 → エラー）
- 日付が存在しない場合（例：20250230 → エラー）
- 勤務時間が3桁までの数値でない場合（例：aaa → エラー）
- カラムが欠けている、もしくは空白の場合（例：,,20250210,180 → エラー）

■6. エラーハンドリング
- 不正なレコードはスキップ。
- エラーの詳細をログ出力（標準エラー出力またはファイル）：
  - 例：社員番号が不正：abc
- 致命的でない限り処理は継続。

■7. クラス構成（あくまで例）
- Main：エントリーポイント。全体の流れを制御。
- CsvReader：CSVファイルの読み込みとバリデーション呼び出し。
- Validator：各項目のバリデーションロジックを担当。
- WorkRecord：読み込んだ1件の勤務情報を保持するDTOクラス。
- WorkTimeAggregator：社員番号＋年月単位で勤務時間を集計。
- CsvWriter：出力CSVの書き込み処理。

## 設計書作成
- 基本設計書　20250416　作成
- IF設計書　　20250416　作成
- 詳細設計書　20250416　作成
- テスト仕様書及び結果表　20250417　作成
## ツール
 - Eclipse
 - Gradle
