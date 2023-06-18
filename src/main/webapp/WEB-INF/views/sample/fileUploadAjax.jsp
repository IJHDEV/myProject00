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
	<input class="inputFile" type="file" name="uploadFiles" multiple="multiple" /><br>
	<input class="inputFile" type="file" name="uploadFiles" /><br>
</div>
<button id="btnFileUpload" type="button">File Upload With Ajax</button>
<div class="fileUploadResult">
	<ul>
		<%-- 업로드 후 처리결과가 표시될 영역 --%>
	</ul>
</div> 


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript">

function checkUploadFile(fileName, fileSize){
	var maxAllowedSize = 10485760; // 10MB
	var regExpFileExtension = /(.*)\.(exe|sh|zip|alz|dll|c)$/i;

	//최대 허용 크기 제한 검사
	if (fileSize > maxAllowedSize) {
		alert("업로드 파일의 크기는 1MB를 넘을 수 없습니다.");
		return false;
	}

	//업로드 파일의 확장자 검사
	if (regExpFileExtension.test(fileName)) {
		alert("해당 종류(exe, sh, zip, alz, dll, c)의 파일은 업로드할 수 없습니다.");
		return false;
	}

	return true;
}


var cloneFileInput = $(".uploadDiv").clone(); //clone() 선택된 요소의 자식요소 복사
//console.log("cloneFileInput: \n" + cloneFileInput.html());


//업로드 결과 표시 함수
function showUploadResult(uploadResult) {
	
	if (!uploadResult || uploadResult.length == 0) {
		return;
	} 
	
	var fileUploadResult = $(".fileUploadResult ul");
	var str = "";
	
	$(uploadResult).each(function(i, obj){
		
		var thumbnailFileName = encodeURI(obj.repoPath + "/" + obj.uploadPath 
				  			  + "/s_" + obj.uuid + "_" + obj.fileName);
		
		var fullFileName = encodeURI(obj.repoPath + "/" + obj.uploadPath 
						 + "/" + obj.uuid + "_" + obj.fileName);
		
		if (obj.fileType == "F") {
			
			str += "<li>"
				+  " 	<a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName + "'>"
				+  " 		<img src='${contextPath}/resources/img/attach.png'"
				+  " 		 	 alt='No Icon' style='height:18px;width:18px;'> " + obj.fileName
				+  " 	</a>"
				+  "	<span data-filename='" + fullFileName + "' data-filetype='F'>[삭제]</span>"
				+  "</li>";
				
		} else if (obj.fileType == "I") {
			
			str += "<li>"
//				+  " 	<a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName + "'>"
				+  " 	<a href=\"javascript:showImage('" + fullFileName + "')\">"
				+  " 		<img src='${contextPath}/displayThumbnail?fileName=" + thumbnailFileName + "'"
				+  "		 	 alt='No Icon' style='height:18px;width:18px;'> " + obj.fileName
				+  "	</a>"
				+  "	<span data-filename='" + thumbnailFileName + "' data-filetype='I'>[삭제]</span>"
				+  "</li>";
		}
		
	});
	
	fileUploadResult.append(str);
} // showUploadResult-end


//함수: 이미지를 다운로드받아서 웹브라우저에 표시
function showImage(fullFileName) {
	$(".bigImageWrapper").css("display", "flex").show();
	$(".bigImage").html("<img src='${contextPath}/fileDownloadAjax?fileName=" + fullFileName + "'>")
//				  .animate({width:'100%', height:'100%'}, 3000);
				  .animate({height:'100%'}, 1000);
			
}

//표시된 이미지 제거
$(".bigImageWrapper").on("click", function(){
	$(".bigImage").animate({width:'0%', height:'0%'}, 1000);
	
	setTimeout(function(){
		$(".bigImageWrapper").hide();
	}, 1000);
	
//	$(".bigImageWrapper").hide();
	
});



//업로드 요청
$("#btnFileUpload").on("click", function(){
	var formData = new FormData();
	var inputFiles = $("input[name='uploadFiles']");
	
	
/*	
	//input이 하나인 경우 (name요소로 선택시 input이 하나이더라도 배열로 선택됨)
	var myFiles = inputFiles[0].files;
	
	if(myFiles == null || myFiles.length == 0) {
		return;
	}

	for (var i = 0; i < myFiles.length; i++) {
		formData.append("uploadFiles", myFiles[i]);
	}
*/	


	//input요소가 여러개일 경우 중첩 for문을 이용
	//input 요소의 파일을 담을 배열 변수 선언
	var myFiles = [];
	
	for (var i = 0; i < inputFiles.length; i++) { //file 타입의 input에 대해 files 속성 사용 가능
		myFiles = inputFiles[i].files;
	
		for (var j = 0; j < myFiles.length; j++) {
			if (!checkUploadFile(myFiles[j].name, myFiles[j].size)) {
				return;
			}
			
			formData.append("uploadFiles", myFiles[j]);	//formData에 inputFiles[0].files [0], [1], [2], ..., inputFiles[1].files [0], [1], [2]... 순으로 append
		}
	}
	
	$.ajax({
		type: "post",
		url: "${contextPath}/fileUploadAjaxAction",
		data: formData,
		contentType: false, //contentType에 MIME type 지정하지 않음	
		processData: false, //contentType에 설정된 형식으로 인코딩 처리하지 않음
		dataType: "json",
		success: function(uploadResult){
			console.log(uploadResult);
			
			$(".uploadDiv").html(cloneFileInput.html());
			
			//$(".uploadDiv .inputFile").val("");
		 	
			//$(".uploadDiv .inputFile").each(function(i, inputFile){
			//	$(this).val("");
			//});
		 	
			//$(".uploadDiv .inputFile").each(function(i, e){
			//	$(e).val("");
			//});
			
			showUploadResult(uploadResult);
			
		}
	});
}); //업로드 요청-end


//업로드 파일 삭제
$(".fileUploadResult ul").on("click", "li span", function(){
	var targetFileName = $(this).data("filename");
	var targetFileType = $(this).data("filetype");
	var parentLi = $(this).parent();
	
	$.ajax({
		type: "post",
		url: "${contextPath}/deleteFile",
		data: {fileName: targetFileName, fileType: targetFileType},
		dataType: "text",
		success: function(result) {
			if (result == "S") {
				alert("파일이 삭제되었습니다.");
				parentLi.remove();
				//$(this).parent().remove(); 이벤트 위임으로 삭제하면 동작 안함
			} else {
				if(confirm("파일 삭제 오류: 파일이 없음.\n화면에서 항목을 삭제하시겠습니까?")) {
					parentLi.remove();
					alert("항목이 삭제되었습니다.");
				}
			}
		}
		
	}); //ajax-end
});


</script>


</body>
</html>