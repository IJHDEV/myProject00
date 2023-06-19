<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<%@include file="../myinclude/myheader.jsp" %>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">Board - Register</h3>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>게시물 등록</h4></div>
                <!-- /.panel-heading -->
                <div class="panel-body">
					
					<form role="form" action="${contextPath }/myboard/register" method="post" name="frmBoard">
						<div class="form-group">
						    <label>글제목</label>
						    <input class="form-control" name="btitle" placeholder="Enter text">
					    </div>
					    <div class="form-group">
						    <label>글내용</label>
						    <textarea class="form-control" rows="3" name="bcontent" placeholder="Enter text"></textarea>
					    </div>
						<div class="form-group">
						    <label>작성자</label>
						    <input class="form-control" name="bwriter" 
						    	   value='<sec:authentication property="principal.username"/>' 
						    	   readonly="readonly">
					    </div>
					    <button type="button" class="btn btn-primary" id="btnRegister" onclick="sendBoard()">등록</button>
					    <button type="button" class="btn btn-warning" data-oper="list"
					    		onclick="location.href='${contextPath}/myboard/list'">취소</button><%-- 
					    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> --%>
					    <sec:csrfInput/>		
					    
					    
					</form><%--
					<br>
               		<div class="form-group uploadDiv">
               			<label>파일 첨부</label>
               			<input class="btn btn-primary inputFile" type="file" name="uploadFiles" multiple="multiple" /><br>
					</div>
					<div class="form-group fileUploadResult">
						<ul></ul>
					</div> --%>
					 
                </div><%-- /.panel-body --%>
            </div><%-- /.panel --%>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>
      
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><label>파일 첨부</label></div>
                <%-- /.panel-heading --%>
                <div class="panel-body">
					
					<div class="form-group uploadDiv">
						<input id="inputFile" class="btn btn-primary inputFile" type="file" name="uploadFiles" multiple="multiple" /><br>
					</div>
					<div class="form-group fileUploadResult">
						<ul>
							<%-- 업로드 후 처리결과가 표시될 영역 --%>
						</ul>
					</div> 
                
                </div><%-- /.panel-body --%>
            </div><%-- /.panel --%>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>
    
</div><%-- /#page-wrapper --%>

<script>

//새 글 등록
function sendBoard(){
	var frmBoard = document.frmBoard;
	var btitle = frmBoard.btitle.value;
	var bcontent = frmBoard.bcontent.value;
	var bwriter = frmBoard.bwriter.value;
	
	if( btitle.length == 0 || bcontent.length == 0 || bwriter.length == 0 ) {
		return false;
	} else {
		return true;
	}
}


$("#btnRegister").on("click", function(){
	if (!sendBoard()){
		alert("글제목/글내용/작성자를 모두 입력해야 합니다.");
		return;
	} 
	
	var formObj = $("form[role='form']");
	var strHiddenInputs = "";
	
	$(".fileUploadResult ul li").each(function(i, obj){
		var objLi = $(obj);
		strHiddenInputs 
			+= "<input type='hidden' name='attachFileList[" + i + "].repoPath' value='" + objLi.data("repopath") + "'>"
			+  "<input type='hidden' name='attachFileList[" + i + "].uploadPath' value='" + objLi.data("uploadpath") + "'>"
			+  "<input type='hidden' name='attachFileList[" + i + "].uuid' value='" + objLi.data("uuid") + "'>"
			+  "<input type='hidden' name='attachFileList[" + i + "].fileName' value='" + objLi.data("filename") + "'>"
			+  "<input type='hidden' name='attachFileList[" + i + "].fileType' value='" + objLi.data("filetype") + "'>";
	});
	
	formObj.append(strHiddenInputs);
	formObj.attr("method", "post");
	formObj.attr("action", "${contextPath}/myboard/register");
	
	formObj.submit();
});



//업로드 파일의 확장자 및 최대 크기 제한 검사
function checkUploadFile(fileName, fileSize){
	var maxAllowedSize = 10485760; // 10MB
	var regExpFileExtension = /(.*)\.(exe|sh|alz|dll|c)$/i;

	//최대 허용 크기 제한 검사
	if (fileSize > maxAllowedSize) {
		alert("업로드 파일의 크기는 1MB를 넘을 수 없습니다.");
		return false;
	}

	//업로드 파일의 확장자 검사
	if (regExpFileExtension.test(fileName)) {
		alert("해당 종류(exe, sh, alz, dll, c)의 파일은 업로드할 수 없습니다.");
		return false;
	}

	return true;
}


function showUploadResult(uploadResult) {
	
	var str = "";
	
	if (!uploadResult || uploadResult.length == 0) {
		return;
	} 
	
	var fileUploadResult = $(".fileUploadResult ul");
	
	$(uploadResult).each(function(i, obj){
		
		var fullFileName = encodeURI(obj.repoPath + "/" + obj.uploadPath 
						 + "/" + obj.uuid + "_" + obj.fileName);
		
		if (obj.fileType == "F") {
			
			str += "<li data-repopath='" + obj.repoPath + "'"
				+  "	data-uploadpath='" + obj.uploadPath + "'"
				+  "	data-uuid='" + obj.uuid + "'"
				+  "	data-filename='" + obj.fileName + "'"
				+  "	data-filetype='F' style='height:25px'>"/* 
				+  " 	<a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName + "'>" */
				+  " 		<img src='${contextPath}/resources/img/attach.png'"
				+  " 		 	 alt='No Icon' style='height:18px;width:18px;'> " + obj.fileName/* 
				+  " 	</a>" */
				+  " 	&nbsp;<button type='button' class='btn btn-danger btn-xs' data-filename='" 
				+ 					  fullFileName + "' data-filetype='F'>X</button>"
				+  "</li>";
				
		} else if (obj.fileType == "I") {
			
			var thumbnailFileName = encodeURI(obj.repoPath + "/" + obj.uploadPath 
		  			  			  + "/s_" + obj.uuid + "_" + obj.fileName);
			
			str += "<li data-repopath='" + obj.repoPath + "'"
				+  "	data-uploadpath='" + obj.uploadPath + "'"
				+  "	data-uuid='" + obj.uuid + "'"
				+  "	data-filename='" + obj.fileName + "'"
				+  "	data-filetype='I' style='height:25px'>"/* 
//				+  " 	<a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName + "'>"
				+  " 	<a href=\"javascript:showImage('" + fullFileName + "')\">" */
				+  " 		<img src='${contextPath}/displayThumbnail?fileName=" + thumbnailFileName + "'"
				+  "		 	 alt='No Icon' style='height:18px;width:18px;'> " + obj.fileName/* 
				+  "	</a>" */
				+  " 	&nbsp;<button type='button' class='btn btn-danger btn-xs' data-filename='" 
				+ 					  thumbnailFileName + "' data-filetype='I'>X</button>"
				+  "</li>";
		}
		
	});
	
	fileUploadResult.append(str);
} // showUploadResult-end


//file input 초기화
var cloneFileInput = $(".uploadDiv").clone();


//업로드 요청
$(".uploadDiv").on("change", ".inputFile", function(){
	var formData = new FormData();
	//var inputFiles = $("input[name='uploadFiles']");
	var inputFile = $(this);
	
	var myFiles = inputFile[0].files; // [0] : HTML내용에서 첫번째 input
	
	if(myFiles == null || myFiles.length == 0) {
		return;
	}

	for (var i = 0; i < myFiles.length; i++) {
		if (!checkUploadFile(myFiles[i].name, myFiles[i].size)) {
			return;
		}
		
		formData.append("uploadFiles", myFiles[i]);
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
			
			showUploadResult(uploadResult);
		}
	});
}); //업로드 요청-end


//업로드 파일 삭제
$(".fileUploadResult ul").on("click", "li button", function(){
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

<%@include file="../myinclude/myfooter.jsp" %>

  