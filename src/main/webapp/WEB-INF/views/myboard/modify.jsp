<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<%@include file="../myinclude/myheader.jsp" %>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">Board - Modify</h3>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>게시물 수정/삭제</h4></div>
                <%-- /.panel-heading --%>
                <div class="panel-body">
					                
					<form role="form" method="post" id="frmModify" name="frmModify">
						<div class="form-group">
						    <label>글번호</label>
						    <input class="form-control" name="bno" value="${board.bno }" readonly="readonly">
					    </div>
						<div class="form-group">
						    <label>글제목</label>
						    <input class="form-control" name="btitle" value="${board.btitle }">
					    </div>
					    <div class="form-group">
						    <label>글내용</label>
						    <textarea class="form-control" rows="3" name="bcontent"
						    >${board.bcontent }</textarea>
					    </div>
						<div class="form-group">
						    <label>작성자</label>
						    <input class="form-control" name="bwriter" value="${board.bwriter }" readonly="readonly">
					    </div>
					    <div class="form-group">
							<label>최종수정일</label> [등록일시: <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
																			value="${board.bregDate}"/>]
							<input class="form-control" name="bmodDate"
								   value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.bmodDate}"/>'
								   disabled="disabled" />
						</div>
					    <button type="button" class="btn btn-default btn-frmModify" id="btnModify" data-oper="modify">수정</button>
					    <button type="button" class="btn btn-danger btn-frmModify" id="btnRemove" data-oper="remove">삭제</button>
					    <button type="button" class="btn btn-info btn-frmModify" id="btnList" data-oper="list">취소</button>
					    
						<input type='hidden' name='pageNum' value='${myBoardPaging.pageNum}'>
						<input type='hidden' name='rowAmountPerPage' id="rowAmountPerPage" value='${myBoardPaging.rowAmountPerPage}'>
						<input type='hidden' name='scope' value='${myBoardPaging.scope}'>
						<input type='hidden' name='keyword' value='${myBoardPaging.keyword}'>
						<input type="hidden" name="startDate" value="${myBoardPaging.startDate }">
	               		<input type="hidden" name="endDate" value="${myBoardPaging.endDate }">
	               		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
					</form>
						                
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




var frmModify = $("#frmModify");

$(".btn-frmModify").on("click", function(){
	var operation = $(this).data("oper");
	//alert("operation: " + operation);
	
	if(operation == "modify") {
		var strHiddenInputs = "";
		
		$(".fileUploadResult ul li").each(function(i, objLi){
			
			var objLi = $(objLi);
			
			strHiddenInputs 
				+= "<input type='hidden' name='attachFileList[" + i + "].repoPath' value='" + objLi.data("repopath") + "'>"
				+  "<input type='hidden' name='attachFileList[" + i + "].uploadPath' value='" + objLi.data("uploadpath") + "'>"
				+  "<input type='hidden' name='attachFileList[" + i + "].uuid' value='" + objLi.data("uuid") + "'>"
				+  "<input type='hidden' name='attachFileList[" + i + "].fileName' value='" + objLi.data("filename") + "'>"
				+  "<input type='hidden' name='attachFileList[" + i + "].fileType' value='" + objLi.data("filetype") + "'>";
		});
		
		frmModify.append(strHiddenInputs);
		frmModify.attr("action", "${contextPath}/myboard/modify");
		
	} else if (operation == "remove") {
		frmModify.attr("action", "${contextPath}/myboard/delete");
//		frmModify.attr("action", "${contextPath}/myboard/remove");
		
	} else if (operation == "list") {
		
		var pageNumInput = $("input[name='pageNum']").clone();
		var rowAmountInput = $("#rowAmountPerPage").clone();
		var scopeInput = $("input[name='scope']").clone();
		var keywordInput = $("input[name='keyword']").clone();
		var startDateInput = $("input[name='startDate']").clone();
		var endDateInput = $("input[name='endDate']").clone();
		
		frmModify.empty();
		
		frmModify.attr("action", "${contextPath}/myboard/list").attr("method", "get");
//		frmModify.attr("method", "get");
		
		frmModify.append(pageNumInput);
		frmModify.append(rowAmountInput);
		frmModify.append(scopeInput);
		frmModify.append(keywordInput);
		frmModify.append(startDateInput);
		frmModify.append(endDateInput);
	}
	
	frmModify.submit();
});


var bnoValue = '<c:out value="${board.bno }"/>';

//첨부파일 정보 표시 함수
function showUploadResult(uploadResult) {
	
	var str = "";
	var fileUploadResult = $(".fileUploadResult ul");
	
	if (!uploadResult || uploadResult.length == 0) {
				
		return;
	} 
	
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
				+  " 		 	 alt='No Icon' style='height:18px;width:18px;'>&nbsp;&nbsp;" + obj.fileName/* 
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
				+  "		 	 alt='No Icon' style='height:18px;width:18px;'>&nbsp;&nbsp;" + obj.fileName/* 
				+  "	</a>" */
				+  " 	&nbsp;<button type='button' class='btn btn-danger btn-xs' data-filename='" 
				+ 					  thumbnailFileName + "' data-filetype='I'>X</button>"
				+  "</li>";
		}
		
	});
	
	fileUploadResult.append(str);
} // showUploadResult-end


//var csrfHeaderName = "${_csrf.headerName}";
//var csrfToken = "${_csrf.token}";

//$(document).ajaxSend(function(e, xhr, options){
//	xhr.setRequestHeader(csrfHeaderName, csrfToken);
//});


function getAttachFileInfo() {
	$.ajax({
		type: "get",
		data: {bno: bnoValue},
		url: "${contextPath}/myboard/getFiles",
		dataType: "json",
		success: function(uploadResult) { //첨부파일 목록
			showUploadResult(uploadResult);
		}
	});
}


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
		beforeSend: function(xhr) {
			xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
		},
		success: function(uploadResult){
			console.log(uploadResult);
			$(".uploadDiv").html(cloneFileInput.html());
			
			showUploadResult(uploadResult);
		}
	});
}); //업로드 요청-end


//파일 삭제(수정화면): 브라우저 표시 항목만 삭제
$(".fileUploadResult ul").on("click", "li button", function(){
	
	if(confirm("이 파일을 삭제하시겠습니까?")) {
		var targetLi = $(this).closest("li");
		targetLi.remove();
		alert("파일이 삭제되었습니다.");
	}
});


$(document).ready(function(){
	getAttachFileInfo();
});


</script>



<%@include file="../myinclude/myfooter.jsp" %>

  