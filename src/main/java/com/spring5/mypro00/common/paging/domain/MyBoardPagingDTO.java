package com.spring5.mypro00.common.paging.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MyBoardPagingDTO {
	private int pageNum;
	private int rowAmountPerPage;
	private String scope;
	private String keyword;
	private String startDate;
	private String endDate;
	
	public String[] getScopeArray() {
		return scope == null ? new String[] {} : scope.split("");
	}
	
	//Setter를 이용하여 필드의 값들이 설정됨 
//	public MyBoardPagingDTO() {
//		this.pageNum = 1 ;
//		this.rowAmountPerPage = 10 ;
//	}
	
	
	public MyBoardPagingDTO(Integer pageNum, Integer rowAmountPerPage, String startDate, String endDate) {
		if (pageNum == null || pageNum <= 0) {
			this.pageNum = 1;
		} else {
			this.pageNum = pageNum;
		}
		
		if (rowAmountPerPage == null) {
			this.rowAmountPerPage = 10;
		} else {
			this.rowAmountPerPage = rowAmountPerPage;
		}
		
		if (startDate != null) {
			this.startDate = startDate;
		}
		
		if (endDate != null) {
			this.endDate = endDate;
		}
		
	}

	public String addPagingParamsToURI() {
		UriComponentsBuilder uriBuilder = 
				UriComponentsBuilder.fromPath("")
								  	.queryParam("pageNum", this.pageNum)
								  	.queryParam("rowAmountPerPage", this.rowAmountPerPage)
								  	.queryParam("scope", this.scope)
								  	.queryParam("keyword", this.keyword)
									.queryParam("startDate", this.startDate)
									.queryParam("endDate", this.endDate);
		String uriString = uriBuilder.toUriString();
		System.out.println("생성된 파라미터 추가 URI String: " + uriString);
		
		return uriString;
	}
	
	
}
