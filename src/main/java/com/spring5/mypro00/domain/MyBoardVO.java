package com.spring5.mypro00.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode
//@Data // getter-setter, 기본생성자, equals(), hashCode(), toString() 
public class MyBoardVO {
	private long bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private int bviewsCnt;
	private int breplyCnt;
	private int bdelFlag; // 1: 삭제요청됨, 0: 유지
	private Date bregDate;
	private Timestamp bmodDate;
	
	private List<MyBoardAttachFileVO> attachFileList;

}
