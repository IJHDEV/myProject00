package com.spring5.mypro00.mapper;

import com.spring5.mypro00.domain.MyAuthorityVO;
import com.spring5.mypro00.domain.MyMemberVO;

public interface MyMemberMapper {

	//특정 아이디의 회원 조회: 회원 권한도 함께 조회됨
	public MyMemberVO selectMyMember(String userId);
	
	
	//회원 등록
	public Integer insertMyMember(MyMemberVO member);
	
	
	//회원 권한 한 개 등록
	public Integer insertMyAuthority(MyAuthorityVO myAuthority);
	
	
	
}
