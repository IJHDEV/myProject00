package com.spring5.mypro00.common.fileupload;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadFormController {
	private String repoPath = "C:/myupload";
	
	//form을 통한 다중 파일 업로드 //uploadFiles
	
	//1. 파일 업로드 요청 JSP 페이지 호출
	@GetMapping(value = {"/fileUploadForm"})
	public String callFileUploadFormPage() {
		System.out.println("Form을 통한 업로드 페이지 표시==");
		return "sample/fileUploadForm";
	}
	
	//2. 파일 업로드 처리
	@PostMapping(value = {"/fileUploadFormAction"})
	public String fileUploadActionPost(MultipartFile[] uploadFiles, 
									   @ModelAttribute("ename") String ename) {
		
		String originalFileName = null;
		for(MultipartFile uploadFile: uploadFiles) {
			System.out.println("===============================");
			System.out.println("Upload File Name: " + uploadFile.getOriginalFilename());
			System.out.println("Upload File Size: " + uploadFile.getSize());
//			File saveUploadFile = new File(uploadFileRepoDir, uploadFile.getOriginalFilename());
			
			originalFileName = uploadFile.getOriginalFilename();
			originalFileName = originalFileName.substring(originalFileName.lastIndexOf("\\") + 1);
			File saveUploadFile = new File(repoPath, originalFileName);
			
			try {
				uploadFile.transferTo(saveUploadFile);
			} catch (IllegalStateException | IOException e) {
				System.out.println("error: " + e.getMessage());
			}
		}
		
		return "sample/fileUploadFormResult";
	}
	

}
