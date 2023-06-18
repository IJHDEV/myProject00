package com.spring5.mypro00.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class MyBoardAttachFileVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private long bno;
	private String repoPath = "C:/myupload";

}
