package com.zhidisoft.accumulationfund.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhidisoft.accumulationfund.service.ExportServiceIf;



@Controller
public class ExportController {
	
	@Resource
	ExportServiceIf exportService;
	//ExportServiceImpl2 exportService;
	@RequestMapping("/export")
	public void export(HttpServletResponse response,String idcard,String num) throws InvocationTargetException, ClassNotFoundException,
			IllegalAccessException, IntrospectionException, ParseException, IOException {

		// 清除buffer缓存
		response.reset(); 
		//设置文件类型： application/vnd.ms-excel 
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		//设置响应头：  以附件的形式下载，并指定默认文件名为：table.xlsx
		response.setHeader("Content-Disposition", "attachment;filename=table.xlsx");

		
		
		XSSFWorkbook workbook = null;
		
		//exportService生成要导出的Excel对象： XSSFWorkbook(sheet\header\row\cell)
		if(num.equals("exportModel")) {//判断是否是模板导出
			workbook = exportService.exportExcelModel();
		}else {
			workbook = exportService.exportExcel();
		}
		
		
		OutputStream output;
		try {
			// 从response中获取输出流
			output = response.getOutputStream();			
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			
			// 将要 导出的 Excel对象 写入 输出流
			workbook.write(bufferedOutPut);
			// 刷出 输出流中的所有数据
			bufferedOutPut.flush();
			// 关闭输出流
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
