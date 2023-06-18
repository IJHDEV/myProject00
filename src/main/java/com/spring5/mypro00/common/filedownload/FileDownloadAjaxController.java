package com.spring5.mypro00.common.filedownload;

import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileDownloadAjaxController {
	
	@GetMapping(value = "/fileDownloadAjax",
				produces = "application/octet-stream")
	@ResponseBody
	public ResponseEntity<Resource> fileDownloadActionAjax(String fileName, @RequestHeader("User-Agent") String userAgent){
		
		System.out.println("전달된 파일이름: " + fileName);
		Resource resource = new FileSystemResource(fileName);
		
		if (!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		String downloadName = resource.getFilename();
//		C:\myupload\2023\06\12\3fbfb0fe-cd4d-4d6c-961c-45b7caa5ad1c_참고_계층적데이터검색.pdf
		
		//UUID가 제거된 파일이름
		downloadName = downloadName.substring(downloadName.indexOf("_") + 1); // UUID에는 "_"가 사용되지 않음
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		//윈도우 10, 최신 Edge, Chrome 브라우저에서 아래 if-else문의 구분 의미 없어짐. 
		try {
			if (userAgent.contains("Trident") || userAgent.contains("MSIE") || 
				userAgent.contains("Edge") || userAgent.contains("Edg")) {
//				downloadName = URLEncoder.encode(downloadName, "utf-8"); //IE
				downloadName = new String(downloadName.getBytes("utf-8"), "ISO-8859-1"); //사용 코드
				System.out.println("MS브라우저: " + downloadName); // 콘솔에서는 한글이 모두 깨짐
				
			} else {
//				downloadName = URLEncoder.encode(downloadName, "utf-8");
				downloadName = new String(downloadName.getBytes("utf-8"), "ISO-8859-1"); //사용 코드
				System.out.println("MS 이외의 브라우저: " + downloadName); // 콘솔에서는 한글이 모두 깨짐
			}
			
		} catch (UnsupportedEncodingException e) {
			e.getMessage();
		}
		
		httpHeaders.add("Content-Disposition", "attachment; filename=" + downloadName);
		
		return new ResponseEntity<Resource>(resource, httpHeaders, HttpStatus.OK);
	}
	
	//핵심코드
	/* Resource resource = new FileSystemResource(fileName);
	 * 
	 * String downloadName = resource.getFilename();
	 * HttpHeaders httpHeaders = new HttpHeaders();
	 * try {downloadName = new String(downloadName.getBytes("utf-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {}
	 * 
	 * httpHeaders.add("Content-Disposition", "attachment; filename=" + downloadName);
	 * return new ResponseEntity<Resource>(resource, httpHeaders, HttpStatus.OK);
	 * */
	
	
}
