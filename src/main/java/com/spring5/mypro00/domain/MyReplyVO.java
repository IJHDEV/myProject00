package com.spring5.mypro00.domain;

import java.sql.Timestamp;
import java.util.Date;

//import lombok.Data;
//import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyReplyVO {
	private long rno;
	private String rcontent;
	private String rwriter;
	private Date rregDate;
	private Timestamp rmodDate;
	private long prno;
	private long bno;
	private int rdelFlag;
	private int lvl;

}
