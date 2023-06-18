package com.spring5.mypro00.common.fileupload.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttachFileDTO {
	private String fileName; //원본파일 이름
	private String uploadPath; //업로드 경로 중 yyyy/MM/dd 형식 문자열
	private String uuid;
	private String fileType; //I: 이미지파일, F:이미지가 아닌 파일
	private String repoPath;
	
	

}
