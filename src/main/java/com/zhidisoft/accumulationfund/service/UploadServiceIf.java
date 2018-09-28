package com.zhidisoft.accumulationfund.service;

import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;


public interface UploadServiceIf {
	public void uploadPayerCreditInfoExcel(InputStream in, MultipartFile upfile) throws Exception ;

}
