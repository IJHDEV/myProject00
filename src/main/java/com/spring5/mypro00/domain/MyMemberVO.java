package com.spring5.mypro00.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MyMemberVO {
	
	private String userId;
	private String userPw;
	private String userName;
	private Timestamp mregDate;
	private Timestamp mmodDate;
	private String mdropFlag; //'1' 탈퇴, '0' 유지
	private boolean enabled; //'0' 비활성, '1' 활성
							 //컬럼 데이터유형은 CHAR(1)
	
	private List<MyAuthorityVO> authorityList;
}
