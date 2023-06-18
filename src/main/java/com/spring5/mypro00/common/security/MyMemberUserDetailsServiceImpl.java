package com.spring5.mypro00.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring5.mypro00.domain.MyMemberVO;
import com.spring5.mypro00.mapper.MyMemberMapper;

import lombok.Setter;

public class MyMemberUserDetailsServiceImpl implements UserDetailsService {
	
	@Setter(onMethod_ = {@Autowired})
	private MyMemberMapper myMemberMapper;
	
//	public MyMemberUserDetailsServiceImpl(MyMemberMapper myMemberMapper) {
//		this.myMemberMapper = myMemberMapper;
//	}
	
	
	public MyMemberUserDetailsServiceImpl() {
	}
	
	
//	@Autowired
//	public void setMyMemberMapper(MyMemberMapper myMemberMapper) {
//		this.myMemberMapper = myMemberMapper;
//	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyMemberVO member = myMemberMapper.selectMyMember(username);
		System.out.println("member: " + member);
		
		return member == null ? null : new MyMemberUser(member);
	}

	

}
