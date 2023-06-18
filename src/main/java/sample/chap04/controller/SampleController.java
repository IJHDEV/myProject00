package sample.chap04.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;
import sample.chap04.domain.SampleDTO;
import sample.chap04.domain.SampleDTOList;
import sample.chap04.domain.TodoDTO;

@Controller
@Log4j
@RequestMapping(value = "/sample/*")
public class SampleController {
	
//	@RequestMapping(value = "/1", method = RequestMethod.GET)
//	public void basic1() {
//		log.info("basic1============");
//	}
//	
//	@RequestMapping(value = "/2")
//	public void basic2() {
//		log.info("basic2============");
//	}
//	
//	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
//	public void basic3() {
//		log.info("basic3============");
//	}

//////////////////////////////////////////////////////
	
	@GetMapping(value = "/basicOnlyGet")
	public void basicGet() {
		log.info("basicGet==========");
	}
	
	
	@PostMapping("/basicOnlyPost")
	public void basicPost() {
		log.info("basicPost=========");
	}
	
	
//////////////////////////////////////////////////////
	
	
	@GetMapping(value = "/ex01")
	public void myEx01(SampleDTO dto) {
		System.out.println("dto.name: " + dto.getName());
		System.out.println("dto.age: " + dto.getAge());
		log.info("==================: " + dto);
	} // http://localhost:8080/mypro00/sample/ex01?name=홍길동&age=24
	
	
	@GetMapping(value = "/ex02")
	public void myEx02(String name, int age) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
	} // http://localhost:8080/mypro00/sample/ex02?name=홍길동&age=24
	
	
//	@GetMapping(value = "/ex022")
//	public void myEx022(@RequestParam("name") String name1, @RequestParam("age") Integer age1) {
//		System.out.println("name1: " + name1);
//		System.out.println("age1: " + age1);
//	} // http://localhost:8080/mypro00/sample/ex022?name=홍길동&age=24
	
	
	@GetMapping(value = "/ex022")
	public String myEx022(@RequestParam("name") String name1, @RequestParam("age") Integer age1) {
		System.out.println("name1: " + name1);
		System.out.println("age1: " + age1);
		
		return "sample/ex04";
	} // http://localhost:8080/mypro00/sample/ex022?name=홍길동&age=24
	
	
	@GetMapping("/ex02List")
	public void myEx02List(@RequestParam("myIds") ArrayList<String> myIds) {
		log.info("========myIds: " + myIds);
	} // http://localhost:8080/mypro00/sample/ex02List?myIds=홍길동&myIds=슈퍼맨&myIds=배트맨
	
	
	@GetMapping("/ex022List") // 값 전달 안됨
	public void myEx022List(ArrayList<String> myIds) {
		log.info("========myIds: " + myIds);
	} // http://localhost:8080/mypro00/sample/ex022List?myIds=홍길동&myIds=슈퍼맨&myIds=배트맨
	
	
	@GetMapping("/ex02Array")
	public void myEx02Array(@RequestParam("myIds") String[] myIds) {
		log.info("========myIds.length: " + myIds.length);
		log.info("========myIds: " + Arrays.toString(myIds));
	} // http://localhost:8080/mypro00/sample/ex02Array?myIds=홍길동&myIds=슈퍼맨&myIds=배트맨
	
	
	@GetMapping("/ex022Array") // 값 전달됨
	public void myEx022Array(String[] myIds) {
		log.info("========myIds.length: " + myIds.length);
		log.info("========myIds: " + Arrays.toString(myIds));
	} // http://localhost:8080/mypro00/sample/ex022Array?myIds=홍길동&myIds=슈퍼맨&myIds=배트맨
	
	
	@GetMapping("/ex02Bean")
	public void ex02Bean(SampleDTOList list) {
		log.info("list: " + list); 
		
	} // http://localhost:8080/mypro00/sample/ex02Bean?list[0].name=홍길동&list[1].name=슈퍼맨&list[2].age=24
	  //[ : %5B , ] : %5D
	  // http://localhost:8080/mypro00/sample/ex02Bean?list%5B0%5D.name=홍길동&list%5B1%5D.name=슈퍼맨&list%5B2%5D.age=24
	
	
	@GetMapping("/ex03")
	public void ex03(TodoDTO todo) {
		log.info("todo: " + todo);
	} // http://localhost:8080/mypro00/sample/ex03?title=홍길동&dueDate=2023-05-28
	
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, String page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	} // http://localhost:8080/mypro00/sample/ex04?name=홍길동&age=24&page=1
	//기본타입, Wrapper, String 타입 값은 jsp로 전달되지 못함
	
	
	@GetMapping("/ex05")
//	public String ex05(SampleDTO dto, @ModelAttribute("page1") String page) {
	public String ex05(SampleDTO dto, @ModelAttribute("page") String page1) {
		log.info("dto: " + dto);
//		log.info("page: " + page);
		log.info("page: " + page1);
		
		return "/sample/ex04";
	} // http://localhost:8080/mypro00/sample/ex05?name=홍길동&age=24&page=1
	// 1. 요청받은 param 이름과 동일한 이름으로 값을 받아서 String 객체에 대입해줌
	// 2. 받은 값을 그대로 jsp에 넘김
	
	
	@GetMapping("/ex055")
	public String ex055(SampleDTO dto, String page, Model model) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		log.info("model: " + model );
		
		model.addAttribute("page1", page);
		
		return "/sample/ex04";
	} // http://localhost:8080/mypro00/sample2/ex055?name=홍길동&age=24&page=1
	// 속성으로 바인딩할때 이름 지정 가능
	
	
	@GetMapping("/ex055List")
	public String myEx055List(@RequestParam("myIds") ArrayList<String> myIds, SampleDTO dto, Model model) {
		log.info("myIds: " + myIds);
		log.info("dto: " + dto);
		log.info("model: " + model);
		
		model.addAttribute("myIds", myIds);
		model.addAttribute("dto", dto);
		log.info("model: " + model);
		
		return "/sample/ex04";
	} // http://localhost:8080/mypro00/sample/ex055List?myIds=홍길동&myIds=슈퍼맨&myIds=배트맨&name=이순신&age=24

	
	@GetMapping("/ex0555List")
	public String myEx0555List(@ModelAttribute("myIds") ArrayList<String> myIds, 
							   @ModelAttribute("name") String name,
							   @ModelAttribute("age") int age) {
		log.info("myIds: " + myIds);
		
		return "/sample/ex04";
	} // http://localhost:8080/mypro00/sample/ex0555List?myIds=홍길동&myIds=슈퍼맨&myIds=배트맨&name=이순신&age=24
	// ArrayList 타입 첫번째 값 하나만 전달됨
		
	
	@GetMapping("/ex0555Array")
	public String myEx0555Array(@ModelAttribute("myIds") String[] myIds, 
								@ModelAttribute("name") String name,
								@ModelAttribute("age") int age) {
		log.info("myIds: " + Arrays.toString(myIds));
		
		return "/sample/ex04";
	} // http://localhost:8080/mypro00/sample/ex0555Array?myIds=홍길동&myIds=슈퍼맨&myIds=배트맨&name=이순신&age=24
	// Array, ArrayList 같은 결과
	
	
	@GetMapping("/ex06")
	public String ex06(String name, int age, int page) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("page: " + page);
		try {
			name = URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		
//		return "redirect:/ex04.jsp"; //contextPath를 포함하면 안됨
		return "redirect:/ex04.jsp?name=" + name + "&age=" + age + "&page=" + page; 
		
	} // http://localhost:8080/mypro00/sample/ex06?name=이순신&age=24&page=2
	// 웹브라우저가 /ex04.jsp로 요청하도록 url이 전달됨
	
	
	@GetMapping("/ex066")
	public String ex066(String name, int age, int page, RedirectAttributes rttr) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("page: " + page);
		
		rttr.addAttribute("name", name);
		rttr.addAttribute("age", age);
		rttr.addAttribute("page", page);
		
		return "redirect:/ex04.jsp";
//		return "redirect:/ex04.jsp?name=" + name + "&age=" + age + "&page=" + page; 
		
	} // http://localhost:8080/mypro00/sample/ex066?name=이순신&age=24&page=2

	
	@GetMapping("/ex0666")
	public String ex0666(String name, int age, int page, RedirectAttributes rttr, HttpServletRequest request) {
		
		System.out.println("URL: " + request.getRequestURL());
		
		System.out.println("name: " + name);
		System.out.println("age: " + age);
		System.out.println("page: " + page);
		
		rttr.addAttribute("name", name);
		rttr.addAttribute("age", age);
		rttr.addAttribute("page", page);
		
//		return "redirect:/sample/ex055";
		return "redirect:/";
//		return "redirect:/ex04.jsp?name=" + name + "&age=" + age + "&page=" + page; 
		
	} // http://localhost:8080/mypro00/sample/ex0666?name=이순신&age=24&page=2
	
	
	// 메서드 반환타입이 DTO, VO인 경우(void/String이 아닌 경우)
	@GetMapping("/ex07")
	public SampleDTO ex07(SampleDTO dto) {
		System.out.println("name: " + dto.getName());
		System.out.println("age: " + dto.getAge());
		
		return dto;
	} // http://localhost:8080/mypro00/sample/ex07?name=이순신&age=24
	//URL을 기반으로 jsp 파일 호출

	
	@GetMapping("/ex08")
	@ResponseBody // Rest API 어노테이션, jsp파일 호출이 없음 
	public SampleDTO ex08(SampleDTO dto) {
//	public @ResponseBody SampleDTO ex08(SampleDTO dto) {
		System.out.println("name: " + dto.getName());
		System.out.println("age: " + dto.getAge());
		
		return dto;
	} // http://localhost:8080/mypro00/sample/ex08?name=이순신&age=24
	//URL을 기반으로 jsp 파일 호출
	
	
	//반환타입이 ResponseEntity<E>인 경우, jsp 호출을 안함
	
	@GetMapping("/ex09")
	public ResponseEntity<String> ex09(SampleDTO dto) {
		log.info("/ex09====================");
//		{"name": "홍길동", "age": 24}
		
		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add("Content-Type", "text/plain;charset=utf-8");
		httpHeaders.add("Content-Type", "application/json;charset=utf-8");
		
		String myJsonStr = "{\"name\": \"" + dto.getName() + "\", \"age\": " + dto.getAge() + "}";
		
//		return new ResponseEntity<String>(myJsonStr, HttpStatus.NOT_FOUND);
		return new ResponseEntity<String>(myJsonStr, httpHeaders, HttpStatus.OK);
	}
	
	
	//commons=fileupload 라이브러리를 이용한 파일 업로드 처리
	//1. Upload JSP 페이지 호출 메서드 작성
//	@GetMapping("/exUpload")
//	public String exUpload() {
//		return "/sample/exUpload";
//	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
	}
	
	private static String uploadFolder = "C:/myupload";
	
	//2. 파일업로드 처리 메서드 작성
	@PostMapping("/exUploadPost")
//	public void exUploadPost(MultipartFile myFile, SampleDTO dto) { // 전송되는 파일 하나일 때 
	public void exUploadPost(MultipartFile[] myFiles, SampleDTO dto) {
//	public void exUploadPost(ArrayList<MultipartFile> myFiles, SampleDTO dto) {
		log.info("name: " + dto.getName());
		log.info("age: " + dto.getAge());
		
//		String uploadFolder = "C:/myupload";
		
		System.out.println("myFiles 길이: " + myFiles.length);
		log.info("파일 업로드 시작=======");
		
		//MultipartFile[] 타입 매개변수 사용시
		for (MultipartFile myFile : myFiles) {
			log.info("--업로드 파일이름: " + myFile.getOriginalFilename() + " : " + myFile.getOriginalFilename().length() );
			log.info("--업로드 파일크기: " + myFile.getSize());
			System.out.println("myFile: " + myFile.toString());
			
			if (myFile.getSize() > 0) {
				File saveFile = new File(uploadFolder, myFile.getOriginalFilename());
				
				try {
					myFile.transferTo(saveFile);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		//업로드 테스트
		//업로드 페이지 표시: http://localhost:8080/mypro00/sample/exUpload
		
		
		//ArrayList<MultipartFile> 타입 매개변수 사용시
//		myFiles.forEach(myFile -> {
//			log.info("파일 업로드 시작=======");
//			log.info("--업로드 파일이름: " + myFile.getOriginalFilename() + " : " + myFile.getOriginalFilename().length());
//			log.info("--업로드 파일크기: " + myFile.getSize());
//			System.out.println("myFile: " + myFile.toString());
//		
//			if (myFile.getSize() > 0) {
//				File saveFile = new File(uploadFolder, myFile.getOriginalFilename());
//				
//				try {
//					myFile.transferTo(saveFile);
//				} catch (IllegalStateException | IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		
	}
	
	
}
