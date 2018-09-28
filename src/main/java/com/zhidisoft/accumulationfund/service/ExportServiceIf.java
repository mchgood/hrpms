package com.zhidisoft.accumulationfund.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zhidisoft.accumulationfund.entity.AccumulationFund;
import com.zhidisoft.result.PageBean;

public interface ExportServiceIf {

  XSSFWorkbook exportExcelInfo(List<AccumulationFund> list)throws InvocationTargetException, 
  ClassNotFoundException, IntrospectionException, 
  ParseException, IllegalAccessException ;
  public XSSFWorkbook exportExcel()throws InvocationTargetException, ClassNotFoundException, IllegalAccessException, IntrospectionException, ParseException ;
  public XSSFWorkbook exportExcelModel() throws InvocationTargetException, ClassNotFoundException, IllegalAccessException, IntrospectionException, ParseException;		
}
