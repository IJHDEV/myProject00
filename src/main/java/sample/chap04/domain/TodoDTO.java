package sample.chap04.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class TodoDTO {
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;

}
