package com.spring5.mypro00.common.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring5.mypro00.common.fileupload.domain.AttachFileDTO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class FileUploadAjaxController {
	private String repoPath = "C:/myupload";
	
	//1. 파일 업로드 요청 JSP 페이지 호출
	@GetMapping(value = "/fileUploadAjax")
	public String callFileUploadAjaxPage() {
		System.out.println("==[Ajax-요청-업로드] 페이지 표시==");
		return "sample/fileUploadAjax";
	}
	
	// 이미지 파일인지 여부 판단
	private boolean checkIsImageFile(File uploadFile) {
		String contentType = null;
		try {
			contentType = Files.probeContentType(uploadFile.toPath());
			System.out.println("업로드 파일 타입: " + contentType);
			return contentType.startsWith("image"); //이미지 파일은 MIME 타입 image로 시작
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
	//날짜 형식 경로 문자열 생성 메서드
	private String getDateFmtPathName() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		String dateFmtPathName = simpleDateFormat.format(new Date());
		
		return dateFmtPathName;
//		return dateFmtPathName.replace("/", File.separator); // "/"를 "\" 로 변환(Window 경로구분자)
	}
	
	
	//2. 파일 업로드 처리
	@PostMapping(value = {"/fileUploadAjaxAction"},
				 produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> fileUploadActionPost(MultipartFile[] uploadFiles) {
		
		List<AttachFileDTO> attachFileList = new ArrayList<AttachFileDTO>();
		
		String dateFmtPathName = getDateFmtPathName();
		
		File fileUploadPath = new File(repoPath, dateFmtPathName);
//									   C:\myupload\2023/06/09

		if (!fileUploadPath.exists()) {
			fileUploadPath.mkdirs();
		} 
		
		String uploadFileName = null;
		
		for(MultipartFile uploadFile: uploadFiles) {
			System.out.println("===============================");
			System.out.println("Upload File Name: " + uploadFile.getOriginalFilename());
			System.out.println("Upload File Size: " + uploadFile.getSize());
//			File saveUploadFile = new File(uploadFileRepoDir, uploadFile.getOriginalFilename());
			
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			
			uploadFileName = uploadFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			attachFileDTO.setFileName(uploadFileName);
			attachFileDTO.setUploadPath(dateFmtPathName);
			attachFileDTO.setRepoPath(repoPath);
			
			//UUID를 이용한 고유한 파일이름 적용
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
//			System.out.println("uuid 추가된 파일이름: " + uploadFileName);
			
			attachFileDTO.setUuid(uuid.toString());

			
//			File saveUploadFile = new File(uploadFileRepoDir, uploadFileName);
			File saveUploadFile = new File(fileUploadPath, uploadFileName);
			
			try {
				uploadFile.transferTo(saveUploadFile);
				
				if (checkIsImageFile(saveUploadFile)) {
					FileOutputStream fileoutputStream = 
							new FileOutputStream(new File(fileUploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(uploadFile.getInputStream(),
												  fileoutputStream, 
												  20, 20);
					fileoutputStream.close();
					attachFileDTO.setFileType("I");
				} else {
					attachFileDTO.setFileType("F");
				}
				
			} catch (IllegalStateException | IOException e) {
				System.out.println("error: " + e.getMessage());
			}
			
			attachFileList.add(attachFileDTO);
			
		} // for-end
		
		return new ResponseEntity<List<AttachFileDTO>>(attachFileList, HttpStatus.OK);
	}
	
	
	//썸네일 파일 전송
	@GetMapping ("/displayThumbnail")
	@ResponseBody
	public ResponseEntity<byte[]> sendThumbnailFile(String fileName) {
		System.out.println("fileName: " + fileName);
		File file = new File(fileName);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK); 
			
		} catch (IOException e) {
			e.getMessage();
		}
		
		return result;
	}
	
	
	//서버에 저장된 업로드 파일 삭제
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String fileType){
		System.out.println("fileName: " + fileName);
		System.out.println("fileType: " + fileType);
		
		//일반파일과 썸네일 파일이름(경로명 포함)이 전달됨
		
		try {
			fileName = URLDecoder.decode(fileName, "utf-8");
			System.out.println("Decoded_fileName: " + fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		File delFile = new File(fileName);
		
		boolean delResult = delFile.delete();
		
		if(fileType.equals("I")) {
			//원본이미지파일 삭제
			delFile = new File(fileName.replace("s_", ""));
			
			boolean delResultForImage = delFile.delete();
			
			delResult = delResult && delResultForImage;
		}
		
		return delResult ? new ResponseEntity<String>("S", HttpStatus.OK)
						 : new ResponseEntity<String>("F", HttpStatus.OK);
		
	}
	
	

}
