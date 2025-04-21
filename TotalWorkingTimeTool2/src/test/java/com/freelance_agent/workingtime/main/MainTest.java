package com.freelance_agent.workingtime.main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.freelance_agent.workingtime.csv.CsvReader;
import com.freelance_agent.workingtime.csv.CsvWriter;
import com.freelance_agent.workingtime.dto.WorkRecord;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Main.class}) // Mainクラスで使用される依存をモック化するために準備
public class MainTest {

    @Test
    public void testMain_valid() throws Exception {
        // モック作成
        CsvReader mockCSVReader = mock(CsvReader.class);
        CsvWriter mockCSVWriter = mock(CsvWriter.class);
        WorkRecord mockRecord = new WorkRecord("001", "テスト太郎", "20250210", "180");
        List<WorkRecord> mockList = new ArrayList<>();
        mockList.add(mockRecord);

        // モックの動作を設定
        when(mockCSVReader.readCSV("test.csv")).thenReturn(mockList);
        doNothing().when(mockCSVWriter).writeCsv("worktime_summary.csv", mockList, false);

        // PowerMockitoで`new`呼び出しをモック化
        PowerMockito.whenNew(CsvReader.class).withNoArguments().thenReturn(mockCSVReader);
        PowerMockito.whenNew(CsvWriter.class).withNoArguments().thenReturn(mockCSVWriter);

        // Main.mainを実行
        Main.main(new String[]{"test.csv","N"});
        

        // モックの呼び出しを検証
        verify(mockCSVReader, times(1)).readCSV("test.csv");
        verify(mockCSVWriter, times(1)).writeCsv("worktime_summary.csv", mockList, false);
    }
    @Test
    public void testMain_valid_append() throws Exception {
        // モック作成
        CsvReader mockCSVReader = mock(CsvReader.class);
        CsvWriter mockCSVWriter = mock(CsvWriter.class);
        WorkRecord mockRecord = new WorkRecord("001", "テスト太郎", "20250210", "180");
        List<WorkRecord> mockList = new ArrayList<>();
        mockList.add(mockRecord);

        // モックの動作を設定
        when(mockCSVReader.readCSV("test.csv")).thenReturn(mockList);
        doNothing().when(mockCSVWriter).writeCsv("worktime_summary.csv", mockList, true);

        // PowerMockitoで`new`呼び出しをモック化
        PowerMockito.whenNew(CsvReader.class).withNoArguments().thenReturn(mockCSVReader);
        PowerMockito.whenNew(CsvWriter.class).withNoArguments().thenReturn(mockCSVWriter);

        // Main.mainを実行
        Main.main(new String[]{"test.csv","A"});
        

        // モックの呼び出しを検証
        verify(mockCSVReader, times(1)).readCSV("test.csv");
        verify(mockCSVWriter, times(1)).writeCsv("worktime_summary.csv", mockList, true);
    }
    @Test
    public void testMain_validate() throws Exception {
        // モック作成
        CsvReader mockCSVReader = mock(CsvReader.class);
        CsvWriter mockCSVWriter = mock(CsvWriter.class);
        List<WorkRecord> mockList = new ArrayList<>();
        
        // モックの動作を設定
        when(mockCSVReader.readCSV("test.csv")).thenReturn(mockList);
        doNothing().when(mockCSVWriter).writeCsv("worktime_summary.csv", mockList, false);

        // PowerMockitoで`new`呼び出しをモック化
        PowerMockito.whenNew(CsvReader.class).withNoArguments().thenReturn(mockCSVReader);
        PowerMockito.whenNew(CsvWriter.class).withNoArguments().thenReturn(mockCSVWriter);

        // Main.mainを実行
        Main.main(new String[]{"test.csv"});
        

        // モックの呼び出しを検証
        verify(mockCSVReader, times(1)).readCSV("test.csv");
        verify(mockCSVWriter, times(0)).writeCsv("worktime_summary.csv", mockList, false);
    }
    @Test
    public void testMain_invalid() throws Exception {
        // モック作成
        CsvReader mockCSVReader = mock(CsvReader.class);
        CsvWriter mockCSVWriter = mock(CsvWriter.class);
        List<WorkRecord> mockList = new ArrayList<>();
        
        // モックの動作を設定
        when(mockCSVReader.readCSV("test.csv")).thenReturn(mockList);
        doNothing().when(mockCSVWriter).writeCsv("worktime_summary.csv", mockList, false);

        // PowerMockitoで`new`呼び出しをモック化
        PowerMockito.whenNew(CsvReader.class).withNoArguments().thenReturn(mockCSVReader);
        PowerMockito.whenNew(CsvWriter.class).withNoArguments().thenReturn(mockCSVWriter);

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        
        // Main.mainを実行
        Main.main(new String[]{});
        

        // モックの呼び出しを検証
        verify(mockCSVReader, times(0)).readCSV("test.csv");
        verify(mockCSVWriter, times(0)).writeCsv("worktime_summary.csv", mockList, false);
        assertEquals( "入力ファイル名を指定してください。\r\n",outContent.toString());
        
        System.setOut(originalOut);
    }
}

