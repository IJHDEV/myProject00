package sample.chap04.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleDTO {
	private String name;
	private int age;

	public SampleDTO() {
		System.out.println("SampleDTO의 기본생성자1..........");
	}
	
	public SampleDTO(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("SampleDTO의 모든 필드 초기화생성자.........");
	}
	
//	public SampleDTO() {
//		this.name = "홍길동";
//		this.age = 24;
//		System.out.println("SampleDTO의 기본생성자2..........");
//	}
	
	
	
}
