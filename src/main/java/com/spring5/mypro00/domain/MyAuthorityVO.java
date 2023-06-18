package com.spring5.mypro00.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MyAuthorityVO {
	private String userId;
	private String authority;
	private Timestamp grantedDate;
}
