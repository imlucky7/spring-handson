package com.springwiz.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import com.springwiz.vo.SalesRecordVO;
import com.springwiz.vo.WeeklyDataVO;

@Component
public class SalesRecordServiceImpl implements SalesRecordService {

	@Override
	public String prepareSalesRecord(InputStream data, String csvPath) throws Exception {
		StringBuilder records = parseExcelData(data);
		prepareCSVData(records, csvPath);
		return "Excel to CSV conversion is successfully completed";
	}

	@Override
	public List<SalesRecordVO> getAllSalesRecord(String csvPath) throws Exception {
		return readCSVFile(csvPath);
	}
	
	private List<SalesRecordVO> readCSVFile(String csvPath) throws Exception {
		final String COMMA_DELIMITER = ",";
		BufferedReader fileReader = null;
		List<SalesRecordVO> records = new ArrayList<SalesRecordVO>();
		try {
			fileReader = new BufferedReader(new FileReader(csvPath));
			String line = "";
			while ((line = fileReader.readLine()) != null) {
				String[] arr = line.trim().split(COMMA_DELIMITER);
				int arrLen = arr.length;
				if(arrLen>1) {
					SalesRecordVO vo = new SalesRecordVO();
					vo.setProduct(arr[0]);
					vo.setTarget(arr[1]);
					List<WeeklyDataVO> weeklyDataList = new ArrayList<WeeklyDataVO>();
					vo.setWeeklyData(weeklyDataList);
					for(int i=2; i<arrLen;){
						WeeklyDataVO dataVO = new WeeklyDataVO();
						dataVO.setSales(arr[i++]);
						dataVO.setAchievement(arr[i++]);
						weeklyDataList.add(dataVO);
					}
					records.add(vo);
				}
			}
			return records;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private StringBuilder parseExcelData(InputStream data) throws Exception {
		final String COMMA_DELIMITER = ",";
	    final String NEW_LINE_SEPARATOR = "\n";
	    StringBuilder records = new StringBuilder();
	    DecimalFormat df = new DecimalFormat("##.##%");
    	DecimalFormat df1 = new DecimalFormat("##.##");
    	Workbook workbook = null;
    	
        try {
        	workbook = WorkbookFactory.create(data);
			Sheet sheet = workbook.getSheetAt(1);
			int totalRows = sheet.getLastRowNum();
			
			for(int i=6; i<totalRows; i++) {
				Row currentRow = sheet.getRow(i);
				if(currentRow != null) {
			    	int cellCount = currentRow.getLastCellNum();
			    	for(int j=0; j<cellCount; j++) {
			    		Cell cell = currentRow.getCell(j);
			    		if(cell != null) {
			                switch (cell.getCellType()) {
			                    case Cell.CELL_TYPE_NUMERIC:
			                    	records.append(df1.format(cell.getNumericCellValue()));
			                    	records.append(COMMA_DELIMITER);
			                        break;
			                    case Cell.CELL_TYPE_STRING:
			                    	String str = cell.getStringCellValue();
			                    	if(!str.trim().isEmpty()) {
			                    		records.append(str);
			                    		records.append(COMMA_DELIMITER);
			                    	}
			                        break;
			                    case Cell.CELL_TYPE_FORMULA:
			                    	records.append(df.format(cell.getNumericCellValue()));
			                    	records.append(COMMA_DELIMITER);
			                        break;
			                }
			    		}
			        }
			    	records.append(NEW_LINE_SEPARATOR);
				}
			}
			
			return records;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			workbook.close();
		}
	}
	
	private void prepareCSVData(StringBuilder records, String csvPath) throws Exception {
		FileWriter fileWriter = null;
        try {
        	fileWriter = new FileWriter(csvPath);
        	fileWriter.append(records);
        } catch(Exception e) {
        	e.printStackTrace();
        	throw e;
        } finally {
        	try{
        		fileWriter.flush();
        		fileWriter.close();
        	} catch(Exception e) {
            	e.printStackTrace();
            	throw e;
            }
        }
	}
}
