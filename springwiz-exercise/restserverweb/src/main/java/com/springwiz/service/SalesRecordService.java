package com.springwiz.service;

import java.io.InputStream;
import java.util.List;

import com.springwiz.vo.SalesRecordVO;

public interface SalesRecordService {
	
	public String prepareSalesRecord(InputStream data, String csvPath) throws Exception;
	public List<SalesRecordVO> getAllSalesRecord(String csvPath) throws Exception;
}
