<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Ajax Page</title>

<style>
/* a {text-decoration: none;} */

.bigImageWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top:0%;
	width: 100%;
	height: 100%;
	background-color: lightgray;
	z-index: 100;
}
.bigImage {
	position: relative;
	display:flex;
	justify-content: center;
	align-items: center;
}
.bigImage img { height: 100%; max-width: 100%; width: auto; overflow: hidden }
</style>

</head>
<body>
<h1>Ajax를 이용한 파일 업로드 페이지</h1>

<div class='bigImageWrapper'>
	<div class='bigImage'>
	<!-- 이미지파일이 표시되는 DIV -->
	</div>
</div>


<div class="uploadDiv">
	<input  type="file" name="myFiles" multiple="multiple" /><br>
	<input  type="file" name="myFiles" /><br>
</div>
<button id="btnFileUpload" type="button">File Upload With Ajax</button>
<div class="fileUploadResult">
	<ul>
		<%-- 업로드 후 처리결과가 표시될 영역 --%>
	</ul>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script type="text/javascript">


//input 초기화를 위해 div 요소의 비어있는 input 요소를 복사해서 저장함.
var cloneInputFile = $(".uploadDiv").clone() ;
console.log("cloneInputFile: " + cloneInputFile.html());

$("#btnFileUpload").on("click", function(){
	
	//FormData() Ajax 파일 전송 시에 사용되는 Web API 클래스의 생성자
	var formData = new FormData() ;
	
	//uploadFiles 이름의 file 유형 input 요소를 변수에 저장
	var inputFiles = $("input[name='myFiles']");
	
	//inputFiles에 저장된 파일들을 files 변수에 저장.
	//inputFiles[0]은 첫번째 input 요소를 의미(input 요소가 하나만 있더라도 [0]을 명시해야 함).
	var yourFiles = inputFiles[0].files ;
	
	if(yourFiles == null || yourFiles.length == 0){
		return ;
	}
	
	console.log(yourFiles);
	

	
	//formData 객체에 파일추가
	for(var i = 0 ; i < yourFiles.length ; i++){
		
		if(!checkUploadFile(yourFiles[i].name, yourFiles[i].size)) {
			//$(".uploadDiv input[name='myFiles']").val("") ;
			$(".uploadDiv").html(cloneInputFile) ;
			return ;
			
		}
		
		formData.append("uploadFiles", yourFiles[i]) ;
		
	}
	
	//url 키에 명시된 주소의 컨트롤러에게 formData 객체를 POST 방식으로 전송.
	$.ajax({
		type: 'post' ,
		url: '${contextPath}/fileUploadAjaxAction' ,
		data: formData ,
		dataType: "json" ,
		contentType: false , //contentType에 MIME 타입을 지정하지 않음.
		processData: false , //contentType에 설정된 형식으로 data를 처리하지 않음.
		//success: function(uploadResult, status){
		success: function(listAttachInfo){
			console.log(listAttachInfo) ;
			
			$(".uploadDiv").html(cloneInputFile.html()) ;
			
			showUploadedFiles(listAttachInfo) ;
			
			
		}
	});
	
}) ;


//업로드 파일의 확장자 및 최대 파일 크기를 제한하는 함수
function checkUploadFile(fileName, fileSize) {
	
	var maxFileSizeAllowed = 104857600 ;
	var regExpFileExtension = /(.*)\.(exe|sh|zip|alz)$/i ;
	
	//최대 허용 크기 제한 검사
	if(fileSize >= maxFileSizeAllowed) {
		alert("업로드 파일의 제한된 크기(1MB)를 초과했습니다.") ;
		return false ;
		
	}
	
	if(regExpFileExtension.test(fileName)) {
		alert("exe, sh, zip, alz 유형의 파일은 업로드 할 수 없습니다.") ;
		return false ;
		
	}
	
	return true ;
}



//JavaScript 업로드 결과 표시 함수
function showUploadedFiles(uploadResult) {
	
	if(!uploadResult || uploadResult.length == 0) {
		return ;	
	}
	
	var fileUploadResult = $(".fileUploadResult ul") ;
	
	var str = "";
	
	$(uploadResult).each(function(i, obj){
		
		var calledPathFileName = obj.repoPath + "/" + obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName ;
		
		//calledPathFileName = encodeURI(calledPathFileName.replace(new RegExp(/\\/g), "/")) ;
		
		console.log("calledPathFileName: "+ calledPathFileName);
	
		if (obj.fileType == "F") {

			str += "<li>"
			    +  "    <a href='${contextPath}/fileDownloadAjax?fileName=" + encodeURI(calledPathFileName) + "'>"
			    +  "        <img src='${contextPath}/resources/img/icon-attach.png'"
			    +  "             alt='No icon' style='height:18px; width: 18px;' />&nbsp;"
			    +           obj.fileName
			    +  "    </a>"
			    +  "    <span data-filename='" + encodeURI(calledPathFileName) + "' data-filetype='F'>[삭제]</span>"
			    +  "</li>" ;
			
		} else  {  //if(obj.fileType == "I")

			var thumbnailPath = encodeURI( obj.repoPath + "/" + obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName) ;
			
			str += "<li>"
				+  "    <a href=\"javascript:showImage('" + calledPathFileName + "')\">"
			    +  "        <img src='${contextPath}/displayThumbnail?fileName=" + thumbnailPath + "'"
			    +  "            alt='No Image' style='height:18px; width: 18px;'/>&nbsp;" 
			    +            obj.fileName
			    +  "    </a>"
			    +  "    <span data-filename='" + encodeURI(calledPathFileName) + "' data-filetype='I'>[삭제]</span>"			    
			    +  "</li>" ;
		}

		
	});

	fileUploadResult.append(str) ;
}


//이미지 표시
function showImage(calledPathImageName){
	console.log("calledPathImageName: " + calledPathImageName ) ;
	console.log("encodeURI(calledPathImageName): " + encodeURI(calledPathImageName) ) ;

	$(".bigImageWrapper").css("display", "flex").show() ;
	
	$(".bigImage").html("<img src='${contextPath}/fileDownloadAjax?fileName=" + encodeURI(calledPathImageName) + "'/>")
	              .animate({width: '100%', height: '100%'}, 1000) ;
	
}

//이미지 제거: 원본 이미지 표시 DIV 클릭 이벤트처리: 클릭 시 1초 후에 이미지 사라짐.
$(".bigImageWrapper").on("click", function(){
	/* 
	$(".bigImage").animate({width: '0%', height: '0%'}, 1000);
	
 	setTimeout(function(){
					$(".bigImageWrapper").hide() ;
	 	    	}, 1000); 
	 */
 	$(".bigImageWrapper").hide() ;
});


//파일 삭제
$(".fileUploadResult ul").on("click", "li span", function(e){
	var targetFileName = $(this).data("filename") ;
	var targetFileType = $(this).data("filetype") ;
	
	var parentLi = $(this).parent() ;
	//var parentLi = $(this).closest("li") ;
	
	$.ajax({
		type: "post" ,
		url: "${contextPath}/deleteUploadedFile" ,
		data: {fileName: targetFileName, fileType: targetFileType} ,
		dataType: "text" ,
		success: function(result) {
			if (result == "SuccessFileDelete") {
				alert("파일이 삭제되었습니다.") ;
				parentLi.remove() ;
				
			} else {
				alert("오류: 파일 삭제 실패") ;
			}
		}
	
		
	});
	

});


</script>



</body>
</html>