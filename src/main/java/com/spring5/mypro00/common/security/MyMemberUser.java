package com.spring5.mypro00.common.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.spring5.mypro00.domain.MyMemberVO;

import lombok.Getter;

@Getter
public class MyMemberUser extends User{

	private static final long serialVersionUID = 1L;
	
	private MyMemberVO member;
	
	public MyMemberUser (MyMemberVO member) {
		super(member.getUserId(), 
			  member.getUserPw(),
			  member.getAuthorityList() //List<MyAuthorityVO>
			  		.stream() //Stream<MyAuthorityVO>
			  		.map(authority -> new SimpleGrantedAuthority(authority.getAuthority())) //Stream<GrantedAuthority>
			  		.collect(Collectors.toList()) //List<GrantedAuthority>
			 );	
		System.out.println("member: " + member.toString());
		System.out.println("MyMemberUser 생성자 => UserDetails 타입으로 변환");
		
		this.member = member;
	}
	

//	public MyMemberUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, authorities);
//	}
	

}
