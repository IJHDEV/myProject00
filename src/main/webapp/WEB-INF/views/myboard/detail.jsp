<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<%@include file="../myinclude/myheader.jsp" %>

<style>

.txtBoxCmt, .txtBoxComment {
	overflow: hidden; resize: vertical; min-height: 100px; color: black;
}

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

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header"
				style="white-space: nowrap;" >Board - Detail&nbsp;&nbsp;<small><c:out value="${board.bno}"/>번 게시물</small></h3>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                	<div class="row">
						<div class="col-md-2" style="white-space: nowrap; height: 45px; padding-top:11px;">
							<strong style="font-size:16px;">${board.bwriter}님 작성</strong>
						</div>
						<div class="col-md-3" style="white-space: nowrap; height: 45px; padding-top:16px;">
							<span class="text-primary" style="font-size: smaller; height: 45px; padding-top: 19px;">
								<span>
									<span>등록일:&nbsp;</span>
									<strong><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" 
															value="${board.bregDate}"/></strong>
									<span>&nbsp;&nbsp;</span>
								</span>
								<span>조회수:&nbsp;<strong><c:out value="${board.bviewsCnt}"/></strong>
								</span>
							</span>
						</div>
						<div class="col-md-7" style="height: 45px; padding-top:6px;"><%-- vertical-align: middle; --%>
							<div class="button-group pull-right">
							<%-- 
								<sec:authorize access="isAuthenticated()">
									<sec:authentication property="principal.username" var="principal"/>
									<c:if test="${username eq board.bwriter }">							
										<button type="button" id="btnToModify" data-oper="modify"
												class="btn btn-primary"><span>수정페이지로 이동</span></button>
									</c:if>
								</sec:authorize> --%>	
								
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="principal"/>
						<c:if test="${principal.username eq board.bwriter }">							
								<button type="button" id="btnToModify" data-oper="modify"
										class="btn btn-primary"><span>수정페이지로 이동</span></button>
						</c:if>
				</sec:authorize>
										
								<button type="button" id="btnToList" data-oper="list"
										class="btn btn-warning"><span>목록페이지로 이동</span></button>
							</div>
						</div>
					</div>
                </div>
                <%-- /.panel-heading --%>
                <div class="panel-body form-horizontal">
					<div class="form-group">
					    <label class="col-sm-2 control-label" style="white-space: nowrap;">글제목</label>
					    <div class="col-sm-10">
					    	<input class="form-control" name="btitle" value="${board.btitle }" readonly="readonly">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" style="white-space: nowrap;">글내용</label>
					    <div class="col-sm-10">
							<textarea class="form-control" rows="3" name="bcontent" style="resize: none;"
									  readonly="readonly"><c:out value="${board.bcontent}"/></textarea>
						</div>
				    </div>
				    <div class="form-group">
						<label class="col-sm-2 control-label" style="white-space: nowrap;">최종수정일</label>
						<div class="col-sm-10">
							<input class="form-control" name="bmodDate"
								   value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.bmodDate}"/>'
								   readonly="readonly" />
						</div>
					</div>
                </div><%-- /.panel-body --%>
            </div><%-- /.panel --%>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>

	<%-- 모달 시작 --%>
	<div class="modal fade" id="yourModal" tabindex="-1" role="dialog" aria-labelledby="yourModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="yourModalLabel">처리 결과</h4>
	            </div>
	            <div class="modal-body" id="yourModal-body">처리 내용</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	            </div>
	        </div><%-- /.modal-content --%>
	    </div><%-- /.modal-dialog --%>
	</div><%-- /.modal --%>
	
	<div class='bigImageWrapper'>
		<div class='bigImage'>
		<!-- 이미지파일이 표시되는 DIV -->
		</div>
	</div>
	
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"><label>첨부파일</label></div>
                <%-- /.panel-heading --%>
                <div class="panel-body"><%-- 
		
					<div class="form-group uploadDiv">
						<input id="inputFile" class="btn btn-primary inputFile" type="file" name="uploadFiles" multiple="multiple" /><br>
					</div> --%>
		
					<div class="form-group fileUploadResult">
						<ul>
							<%-- 업로드 후 처리결과가 표시될 영역 --%>
						</ul>
					</div> 

                </div><%-- /.panel-body --%>
            </div><%-- /.panel --%>
        </div><%-- /.col-lg-12 --%>
    </div><%-- /.row --%>

	<form id="frmSendValue">
		<input type="hidden" name="bno" id="bno" value='<c:out value="${board.bno }"/>'>
		<input type='hidden' name='pageNum' value='${myBoardPaging.pageNum}'>
		<input type='hidden' name='rowAmountPerPage' value='${myBoardPaging.rowAmountPerPage}'>
		<input type='hidden' name='scope' value='${myBoardPaging.scope}'>
		<input type='hidden' name='keyword' value='${myBoardPaging.keyword}'>
		<input type='hidden' name='startDate' value='${myBoardPaging.startDate}'>
		<input type='hidden' name='endDate' value='${myBoardPaging.endDate}'>
	</form>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default" >
			
				<div class="panel-heading">
					<p style="display:in-block;margin-bottom:0px">
						<strong style="padding-top: 2px;">
							<span id="replyRowTotal"></span>
							<span id="replyTotal"></span>
							<span>&nbsp;</span>
						</strong>
						
				<sec:authorize access="isAuthenticated()">							
							<button type="button" id="btnChgCmtReg" class="btn btn-info btn-sm">댓글 작성</button>
							<button type="button" id="btnRegCmt" class="btn btn-primary btn-sm"
									style="display:none">댓글 등록</button>
							<button type="button" id="btnCancelRegCmt" class="btn btn-danger btn-sm"
									style="display:none">취소</button>&nbsp;&nbsp;
					<strong style="padding-top: 2px;">
						<span id="loginUser" style="display:none">
							<sec:authentication property="principal.username"/>님 작성
						</span>
					</strong>
				</sec:authorize>
				
					</p>
				</div> <%-- /.panel-heading --%>
				
				<div class="panel-body"><%-- 댓글 입력창 div 시작 --%>
				
				<sec:authorize access="isAuthenticated()">	
					<div class="form-group" style="margin-bottom: 5px;">
						<textarea class="form-control txtBoxCmt" name="rcontent"
								  placeholder="댓글작성을 원하시면,&#10;댓글 작성 버튼을 클릭해주세요."
								  readonly="readonly"
								 ></textarea>
					</div>
					<hr style="margin-top: 10px; margin-bottom: 10px;"><%-- 댓글 입력창 div 끝 --%>
				</sec:authorize>		
					
					
					<ul class="chat">
					<%-- 댓글 목록 표시 영역 - JavaScript로 내용이 생성되어 표시됩니다.
						<li class="left clearfix commentLi" data-bno="123456" data-rno="12">
							<div>
								<div>
									<span class="header info-rwriter">
										<strong class="primary-font">user00</strong>
										<span>&nbsp;</span>
										<small class="text-muted">2018-01-01 13:13</small>
									</span>
								<p>앞으로 사용할 댓글 표시 기본 템플릿입니다.</p>
								</div>
								<div class="btnsComment" style="margin-bottom:10px">
									<button type="button" style="display:in-block"
											class="btn btn-primary btn-xs btnChgReg">답글 작성</button>
									<button type="button" style="display:none"
											class="btn btn-warning btn-xs btnRegCmt">답글 등록</button>
									<hr class="txtBoxCmtHr" style="margin-top:10px; margin-bottom:10px">
									<textarea class="form-control txtBoxCmtMod" name="rcontent" style="margin-bottom:10px"
											  placeholder="답글작성을 원하시면,&#10;답글 작성 버튼을 클릭해주세요."
											 ></textarea>
								</div>
							</div>
						</li> --%>
					</ul><%-- /.chat --%>

				</div><%-- /.panel-body --%>
				
				<div class="panel-footer text-center" id="showCmtPagingNums">
					<%-- 댓글 목록의 페이징 번호 표시 영역 - JavaScript로 내용이 생성되어 표시됩니다.--%>
				</div>
				
			</div><%-- /.panel --%>
		</div><%-- .col-lg-12 --%>
	</div><%-- .row : 댓글 화면 표시 끝 --%>
	
	<form id="frmCmtPagingValue">
		<input type="hidden" name="pageNum" value="">
		<input type="hidden" name="rowAmountPerPage" value="">
	</form>
	
</div><%-- /#page-wrapper --%>


<script>
var bnoValue = '<c:out value="${board.bno}"/>';
var frmSendValue = $("#frmSendValue");


//게시물 수정 페이지로 이동
$("#btnToModify").on("click", function(){
//	location.href="${contextPath}/myboard/modify?bno=${board.bno}";
	frmSendValue.attr("action", "${contextPath}/myboard/modify");
	frmSendValue.attr("method", "get");
	
	frmSendValue.submit();
});


//게시물 목록 페이지로 이동
$("#btnToList").on("click", function(){
//	location.href="${contextPath}/myboard/list";
	frmSendValue.find("#bno").remove();
	frmSendValue.attr("action", "${contextPath}/myboard/list");
	frmSendValue.attr("method", "get");
	
	frmSendValue.submit();
});


var result = '<c:out value="${result}"/>';

function checkModifyOperation(result) {
    if (result == '' || history.state) {
        return ;
    } else if (result == 'successModify') {
        var myMsg = "글이 수정되었습니다.";
    } 
 
    //$(".modal-body").html(myMsg);
    $("#yourModal-body").html(myMsg);
    $("#yourModal").modal("show");
    
    //alert(myMsg);
    myMsg = ""; 
}

//첨부파일 목록 표시 함수
function showUploadResult(uploadResult) {
	
	var str = "";
	var fileUploadResult = $(".fileUploadResult ul");
	
	if (!uploadResult || uploadResult.length == 0) {
		
		str += "<li>첨부파일이 없습니다.</li>";
		
		fileUploadResult.html(str);
		
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
				+  " 		 	 alt='No Icon' style='height:18px;width:18px;'> " + obj.fileName /* 
				+  " 	</a>" *//*  
				+  "	<span data-filename='" + fullFileName + "' data-filetype='F'>[삭제]</span>" */
				+  "</li>";
				
		} else if (obj.fileType == "I") {
			
			var thumbnailFileName = encodeURI(obj.repoPath + "/" + obj.uploadPath 
		  			  			  + "/s_" + obj.uuid + "_" + obj.fileName);
			
			str += "<li data-repopath='" + obj.repoPath + "'"
				+  "	data-uploadpath='" + obj.uploadPath + "'"
				+  "	data-uuid='" + obj.uuid + "'"
				+  "	data-filename='" + obj.fileName + "'"
				+  "	data-filetype='I' style='height:25px'>"/*  
				+  " 	<a href='${contextPath}/fileDownloadAjax?fileName=" + fullFileName + "'>" *//* 
				+  " 	<a href=\"javascript:showImage('" + fullFileName + "')\">" */ 
				+  " 		<img src='${contextPath}/displayThumbnail?fileName=" + thumbnailFileName + "'"
				+  "		 	 alt='No Icon' style='height:18px;width:18px;'> " + obj.fileName/*  
				+  "	</a>" *//*  
				+  "	<span data-filename='" + thumbnailFileName + "' data-filetype='I'>[삭제]</span>" */
				+  "</li>";
		}
	});
	
	fileUploadResult.html(str);
} // showUploadResult-end



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


//첨부파일 모달 표시
$(".fileUploadResult ul").on("click", "li", function(){
	
	var objLi = $(this);
	
	var fullFileName = encodeURI(objLi.data("repopath") + "/" 
							   + objLi.data("uploadpath") + "/" 
							   + objLi.data("uuid") + "_" 
							   + objLi.data("filename"));
	
	
	if(objLi.data("filetype") == "I") {
		
		$(".modal-content")
		.html("<img src='${contextPath}/fileDownloadAjax?fileName=" 
			+ 		fullFileName + "'" 
			+ " 	width='100%' >");
		
		$("#yourModal").modal("show");
		
	} else {
		self.location.href = "${contextPath}/fileDownloadAjax?fileName=" + fullFileName;	
	} 
	
});


//이미지 표시 모달 감추기
$("#yourModal").on("click", function(){
	$("#yourModal").modal("hide");
});

</script>

<script type="text/javascript" src="${contextPath }/resources/js/mycomment.js"></script>
<script>
//var bnoValue = '<c:out value="${board.bno}"/>';

var csrfHeaderName = "${_csrf.headerName}";
var csrfToken = "${_csrf.token}";

$(document).ajaxSend(function(e, xhr, options){
	xhr.setRequestHeader(csrfHeaderName, csrfToken);
});



var loginUser = "";

<sec:authorize access="isAuthenticated()">
	loginUser = '<sec:authentication property="principal.username"/>';
</sec:authorize>


var commentUL = $(".chat");
var frmCmtPagingValue = $("#frmCmtPagingValue");

<%-- 댓글 목록 표시 함수 --%>
function showCmtList(pageNum) {
	myCommentClsr.getCmtList(
		{bno: bnoValue, pageNum: pageNum || 1},
		
		function(myReplyPagingCreator){
			
			$("#replyRowTotal").html("댓글 행 개수&nbsp;" + myReplyPagingCreator.rowTotal[0] + "개");
			
			$("#replyTotal").html("댓글수&nbsp;" + myReplyPagingCreator.rowTotal[1] + "개");
			
			var lastPageNum = 1;
			if(pageNum == -1) {
				lastPageNum = Math.ceil(myReplyPagingCreator.rowTotal[0] / myReplyPagingCreator.myReplyPaging.rowAmountPerPage);
				showCmtList(lastPageNum);

//				console.log("pageNum: " + pageNum + " : " + "lastPageNum: " + myReplyPagingCreator.lastPageNum);
//				showCmtList(myReplyPagingCreator.lastPageNum);
				
				return;
			}
			
			frmCmtPagingValue.find("input[name='pageNum']").val(pageNum);
			frmCmtPagingValue.find("input[name='rowAmountPerPage']").val(myReplyPagingCreator.myReplyPaging.rowAmountPerPage);
						
			var str = "";
			
			if(!myReplyPagingCreator.replyList || myReplyPagingCreator.replyList.length == 0){
				str +='<li class="left clearfix commentLi" '
					+ ' 	style="text-align: center; background-color: lightCyan;'
					+ ' 		   height: 35px;margin-bottom: 0px;padding-bottom:0px;'
					+ ' 		   padding-top:6px;border: 1px dotted;">'
					+ ' <strong>등록된 댓글이 없습니다.</strong></li>';
				commentUL.html(str);
				return;
			}
			
			for(var i = 0, len = myReplyPagingCreator.replyList.length; i < len; i++) {
				if (myReplyPagingCreator.replyList[i].rdelFlag == 1) {
				str +='<li class="left clearfix commentLi"'
					+ ' 	style="text-align: center; background-color: Moccasin;'
					+ ' 		   height: 35px;margin-bottom: 0px;padding-bottom:0px;'
					+ ' 		   padding-top:6px;"'
					+ '	   data-bno="' + bnoValue + '"'
					+ '    data-rno="' + myReplyPagingCreator.replyList[i].rno + '"'
					+ '	   data-rwriter="' + myReplyPagingCreator.replyList[i].rwriter + '"'
					+ '    data-rdelFlag="' + myReplyPagingCreator.replyList[i].rdelFlag + '">'			
					+ ' <div><em>사용자에 의해 삭제된 댓글입니다.</em></div>'
					+ '</li>';
				} else {
				str +='<li class="left clearfix commentLi" data-bno="' + bnoValue
					+ '"   data-rno="' + myReplyPagingCreator.replyList[i].rno + '"'
					+ '    style="margin-bottom: 0px;padding-bottom: 0px;'
					+ '    padding-top:6px;">'
					+ ' <div>';
					//댓글에 대한 답글 들여쓰기
					if(myReplyPagingCreator.replyList[i].lvl == 1){//댓글 들여쓰기 안함
				str +=' 	<div>';
					} else if (myReplyPagingCreator.replyList[i].lvl == 2){//답글 들여쓰기
				str +=' 	<div style="padding-left:25px">';
					} else if (myReplyPagingCreator.replyList[i].lvl == 3){//답글의 답글 들여쓰기
				str +=' 	<div style="padding-left:50px">';
					} else if (myReplyPagingCreator.replyList[i].lvl == 4){//답글의 답글 들여쓰기
				str +=' 	<div style="padding-left:75px">';
					} else {//답글의 레벨이 5이상이면 동일한 들여쓰기
				str +=' 	<div style="padding-left:100px">';
					}
				str +=' 		<span class="header info-rwriter">'
					+ ' 			<strong class="primary-font">';
					
					if(myReplyPagingCreator.replyList[i].lvl > 1) {//답글인 경우, 앞에 아이콘 표시
				str +=' 				<i class="fa fa-reply fa-fw"></i>&nbsp;';
					}
					
				str += 					myReplyPagingCreator.replyList[i].rwriter
					+ ' 			</strong>'
					+ ' 			<span>&nbsp;</span>'
					+ ' 			<small class="text-muted">'
					+ 					myCommentClsr.showDatetime(myReplyPagingCreator.replyList[i].rmodDate) //수정날짜가 정수값으로 표시됨
					+ ' 			</small>';
					
					if(myReplyPagingCreator.replyList[i].lvl > 1) {
				str +=' 			<small>&nbsp; 답글</small>';
					}
				str +=' 			<small class="text-muted" id="lastModifiedTime">'
					+ ' 				<em>'
					+ 						myCommentClsr.notifyRegisterTime(myReplyPagingCreator.replyList[i].rmodDate)
					+ ' 				</em>'
					+ ' 			</small>'
				str +=' 		</span>'
					+ '			<p data-bno="' + myReplyPagingCreator.replyList[i].bno + '"'
					+ ' 	 	   data-rno="' + myReplyPagingCreator.replyList[i].rno + '"'
					+ ' 	 	   data-rwriter="' + myReplyPagingCreator.replyList[i].rwriter + '"'			
					+ '		 	  >' + myReplyPagingCreator.replyList[i].rcontent + '</p>'
					+ ' 	</div>';
<sec:authorize access="isAuthenticated()">
				str +=' 	<div class="btnsReply" style="margin-bottom:10px">'
					+ ' 		<button type="button" style="display:in-block" '
					+ ' 				class="btn btn-primary btn-xs btnChgReplyReg">'
					+ ' 		<span>답글 작성</span></button>'
					+ '		</div>';	
</sec:authorize>
				str +='	</div>'
					+ '</li>';
				}
						
			} // for-end
			commentUL.html(str);
			
			showCmtPagingNums(myReplyPagingCreator.rowTotal[0], 
							  myReplyPagingCreator.myReplyPaging.pageNum, 
							  myReplyPagingCreator.myReplyPaging.rowAmountPerPage);
		} // callback-end
	); // getCmtList-end
} // showCmtList-end

<%-- 댓글 페이징 번호 표시 --%>

function showCmtPagingNums(rowTotal, pageNum, rowAmountPerPage) {
	var pagingNumCnt = 3;
	var endPagingNum = Math.ceil(pageNum / pagingNumCnt * 1.0) * pagingNumCnt; 
	var startPagingNum = endPagingNum - (pagingNumCnt - 1);
	
	var lastPageNum = Math.ceil(rowTotal * 1.0 / rowAmountPerPage);
	
	if(lastPageNum < endPagingNum) {
		endPagingNum = lastPageNum;
	}
	
	var prev = startPagingNum > 1;
	var next = endPagingNum < lastPageNum;
	
	var str  = '<ul class="pagination pagination-sm" >' ;
	//맨 앞으로
	if(prev) {
	   //맨 앞으로
	   str += '    <li class="page-item" >'
	       +  '         <a class="page-link" href="1" >'
	       +  '             <span aria-hidden="true" >&laquo;</span>'
	       +  '         </a>'
	       +  '    </li>' ;
	   //이전 페이지    
	   str += '    <li class="page-item" >'
	       +  '         <a class="page-link" href="' + (startPagingNum - 1) + '" >'
	       +  '             <span aria-hidden="true" >이전</span>'
	       +  '         </a>'
	       +  '    </li>' ;
	}
	       
	   //선택된 페이지 번호 Bootstrap의 색상표시
	for(var i = startPagingNum ; i <= endPagingNum ; i++){
	   //active는 Bootstrap 클래스 이름
	   var active = ((pageNum == i) ? "active" : "" );
	      
	   str +='     <li class="page-item ' + active + '">'
	       + '         <a class="page-link" href="' + i + '" >'
	        + '             <span aria-hidden="true" >' + i + '</span>'
	        + '         </a>'
	        + '     </li>' ;
	}
	   
	if(next) {
	   //다음 페이지
	   str += '    <li class="page-item" >'
	       +  '         <a class="page-link" href="' + (endPagingNum + 1) + '" >'
	       +  '             <span aria-hidden="true" >다음</span>'
	       +  '         </a>'
	       +  '    </li>' ;
	   //맨마지막으로     
	   str += '    <li class="page-item" >'
	       +  '         <a class="page-link" href="' + lastPageNum + '" >'
	       +  '             <span aria-hidden="true" >&raquo;</span>'
	       +  '         </a>'
	       +  '    </li>' ;
	
	       
	   str += '</ul>' ;
	   
	}
	
	$("#showCmtPagingNums").html(str);
	
}


<%-- 선택된 페이징 번호의 게시물 목록 가져오는 함수: 이벤트 전파 이용 --%>
$("#showCmtPagingNums").on("click", "ul li a", function(e){
	e.preventDefault();
	
	var targetPageNum = $(this).attr("href");
	showCmtList(targetPageNum);
});


//#showCmtPagingNums > ul > li:nth-child(2) > a > span



<%-- 댓글 추가 초기화 함수 --%>
function chgBeforeCmtBtn(){
	$("#btnChgCmtReg").attr("style", "display:in-block");
	$("#btnRegCmt").attr("style", "display:none");
	$("#btnCancelRegCmt").attr("style", "display:none");
	$("#loginUser").attr("style", "display:none");
	$(".txtBoxCmt").val("");
	$(".txtBoxCmt").attr("readonly", true);
}


<%-- 댓글 작성 버튼 클릭 --%>
$("#btnChgCmtReg").on("click", function(){
	chgBeforeReplyBtn();
	chgBeforeCmtReplyMod();
	
	$(this).attr("style", "display:none");
	$("#btnRegCmt").attr("style", "display:in-block;margin-right:2px;");
	$("#btnCancelRegCmt").attr("style", "display:in-block;");
	$("#loginUser").attr("style", "display:in-block;");
	$(".txtBoxCmt").attr("readonly", false);
});


<%-- 댓글 작성 취소 버튼 클릭 --%>
$("#btnCancelRegCmt").on("click", function(){
//	if (!confirm("댓글 입력을 취소하시겠습니까?")) {
//		return;
//	}
	chgBeforeCmtBtn();
});


<%-- 댓글 등록 --%>
$("#btnRegCmt").on("click", function(){
	
	if(!loginUser) {
		alert("로그인 후, 댓글 등록이 가능합니다.");
		return;
	}
	
	
	var txtBoxCmt = $(".txtBoxCmt").val();
	var comment = {bno: bnoValue, rcontent: txtBoxCmt, rwriter: loginUser};
	
	myCommentClsr.registerCmt(
		comment,
		function(result){
			if(result == "CommentRegisterSuccess") {
				alert("댓글이 등록되었습니다.");
				showCmtList(-1);
				chgBeforeCmtBtn();
			} else {
				alert("죄송합니다. 서버 장애로 댓글 등록이 취소되었습니다.");
			}
		}
	);
});


<%-- 답글 작성 버튼 클릭--%> 
<%--소스에 없는 요소 jquery선택자로 바로 선택 불가, on함수의 매개변수로 경로 지정(시작요소 최종요소)--%>
$(".chat").on("click", "li .btnChgReplyReg", function(){
	$("p").attr("style", "display:in-block;");
	
	chgBeforeCmtBtn();
	chgBeforeReplyBtn();
	chgBeforeCmtReplyMod();
	
	var strTxtBoxReply = 
		  "<textarea class='form-control txtBoxReply' name='rcontent' style='margin-bottom:10px;'"
		+ " 		 placeholder='답글작성을 원하시면, &#10;답글 작성 버튼을 클릭해주세요.'"
		+ "			></textarea>"
		+ "<button type='button' class='btn btn-warning btn-xs btnRegReply'>답글 등록</button>"
		+ "<button type='button' class='btn btn-danger btn-xs btnCancelRegReply'"
		+ "		   style='margin-left:4px;'>취소</button>";
		
	$(this).after(strTxtBoxReply);
	$(this).attr("style","display:none;");
		
});


<%-- 답글 작성 초기화 함수 --%>
function chgBeforeReplyBtn(){
	$(".txtBoxReply").remove();
	$(".btnRegReply").remove();
	$(".btnCancelRegReply").remove();
	$(".btnChgReplyReg").attr("style","display:in-block;");
}


<%-- 답글 작성 취소 버튼 클릭--%>
$(".chat").on("click", "li .btnCancelRegReply", function(){
//	if(!confirm("답글 입력을 취소하시겠습니까?")){
//		return;
//	}
	chgBeforeReplyBtn();
});


<%-- 답글 등록 버튼 클릭--%>
$(".chat").on("click", "li .btnRegReply", function(){
	var txtBoxReply = $(this).prev().val();
	var prnoVal = $(this).closest("li").data("rno");
	var reply = {bno: bnoValue, rcontent: txtBoxReply, rwriter: loginUser, prno: prnoVal};
	
	myCommentClsr.registerReply(
		reply,
		function(result){
			if(result == "ReplyRegisterSuccess") {
				alert("답글이 등록되었습니다.");
				
				showCmtList(frmCmtPagingValue.find("input[name='pageNum']").val());
			} else {
				alert("죄송합니다. 서버 장애로 답글 등록이 취소되었습니다.");
			}
		}
	);
});


<%-- 댓글-답글 수정 초기화 --%>
function chgBeforeCmtReplyMod(){
	$(".btnModCmt").remove();
	$(".btnDelCmt").remove();
	$(".btnCancelCmt").remove();
	$(".txtBoxMod").remove();
	$(".chat").find("li.commentLi p").attr("style", "display:in-block");
} 


<%-- 댓글-답글 수정/삭제화면 요소 표시 --%>
$(".chat").on("click", "li.commentLi p", function(){
	chgBeforeCmtBtn();
	chgBeforeReplyBtn();
	chgBeforeCmtReplyMod();
	
	if (!loginUser) {
//		alert("로그인 후 수정이 가능합니다.");
		return;
	}
	
	var rwriter = $(this).data("rwriter");
	
	if (loginUser != rwriter) {
//		alert("작성자만 수정할 수 있습니다.");
		return;
	}
	
	
	$(this).parents("li").find(".btnChgReplyReg").attr("style", "display:none;");
	var rcontent = $(this).text();
	var strTxtBoxReply =
		  "<textarea class='form-control txtBoxMod' name='rcontent' style='margin-bottom:10px;'"
		+ " 		 placeholder='답글작성을 원하시면,&#10;답글 작성 버튼을 클릭해주세요.'"
		+ "			></textarea>"
		+ "<button type='button' class='btn btn-warning btn-sm btnModCmt'>수정</button> "
		+ "<button type='button' class='btn btn-danger btn-sm btnDelCmt'>삭제</button>"
		+ "<button type='button' class='btn btn-info btn-sm btnCancelCmt' style='margin-left: 4px;'>취소</button>";
		
	$(this).after(strTxtBoxReply);
	$(".txtBoxMod").val(rcontent);
	$(this).attr("style", "display:none;");
});


<%-- 댓글-답글 수정/삭제의 취소 버튼 이벤트 처리 --%>
$(".chat").on("click", "li.commentLi .btnCancelCmt", function(){
	chgBeforeCmtReplyMod();
	chgBeforeReplyBtn();
});


<%-- 댓글-답글 수정 --%>
$(".chat").on("click", "li.commentLi .btnModCmt", function(){
	
	if(!loginUser) {
//		alert("로그인 후, 수정이 가능합니다.");
		return;
	}
	
	var rwriterVal = $(this).siblings("p").data("rwriter");
	
	if (loginUser != rwriterVal) {
//		alert("작성자만 수정 가능합니다.");
		return;
	}
	
	
	var rnoVal = $(this).siblings("p").data("rno");
	var rcontentVal = $(this).prev().val();
	
	
	var cmtReply = {bno: bnoValue, rno: rnoVal, rcontent: rcontentVal, rwriter: rwriterVal};
	
	myCommentClsr.modifyCmtReply(
		cmtReply,
		function(result){
			if(result == "ReplyModifySuccess") {
				//alert("수정되었습니다.");
				showCmtList(frmCmtPagingValue.find("input[name='pageNum']").val());
			} else {
				alert("죄송합니다. 서버 장애로 댓글 수정이 취소되었습니다.");
			}
		},
		
		function(err) {
			alert("작성자만 수정 가능합니다.");
		}
	);
});




<%-- 댓글-답글 삭제 요청 --%>
$(".chat").on("click", "li.commentLi .btnDelCmt", function(){
	
	if(!loginUser) {
		alert("로그인 후, 삭제할 수 있습니다.");
		return;
	}
	
	var rwriterVal = $(this).siblings("p").data("rwriter");

	if (loginUser != rwriterVal) {
		//alert("작성자만 삭제할 수 있습니다.");
		return;
	}
	
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}
	
	var rnoVal = $(this).closest("li.commentLi").data("rno");
	var cmtReply = {bno: bnoValue, rno: rnoVal, rwriter: rwriterVal};
	
	myCommentClsr.modifyFlagCmtReply(
		cmtReply,
		function(result){
			if(result == "ReplyRemoveSuccess") {
				alert("댓글이 삭제되었습니다.");
				showCmtList(frmCmtPagingValue.find("input[name='pageNum']").val());
			} else {
				alert("죄송합니다. 서버 장애로 댓글 삭제가 취소되었습니다.");
			}
		}
	);
}); 


<%-- 댓글-답글 삭제: 자식 답글도 CASCADE로 삭제됨 
$(".chat").on("click", "li.commentLi .btnDelCmt", function(){
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}
	var rnoVal = $(this).closest("li.commentLi").data("rno");
	var rwriterVal = $(this).siblings("p").data("rwriter");
	
	var cmtReply = {bno: bnoValue, rno: rnoVal, rwriter: rwriterVal}; 
	
	myCommentClsr.removeCmtReply(
		cmtReply,
		function(result){
			if(result == "ReplyRemoveSuccess") {
				alert("댓글이 삭제되었습니다.");
			} else {
				alert("죄송합니다. 서버 장애로 댓글 삭제가 취소되었습니다.");
			}
			showCmtList();
		}
	);
}); --%>


$(document).ready(function() {
	checkModifyOperation(result);
	
    window.addEventListener("popstate", function(event){
        history.pushState(null, null, location.href);
    });

    history.pushState(null, null, location.href);
    
	//첨부파일 정보 표시
    getAttachFileInfo();
    
	//댓글 표시
    showCmtList(1);
});


</script>

<%@include file="../myinclude/myfooter.jsp" %>


<%-- 
<script>
var bnoValue = '<c:out value="${board.bno}"/>';
//myCommentClsr

/*
myCommentClsr.getCmtList(
	{bno:bnoValue},
	function(replyList) {
		for(var i = 0, len = replyList.length || 0; i < len; i++){
			console.log(replyList[i]);
			console.log(myCommentClsr.showDatetime(replyList[i].rregDate));
		}
	}
);
*/


/*
myCommentClsr.registerCmt(
	{bno:bnoValue, rcontent:"JS댓글등록1", rwriter:"user7"},
	function(result) {
		console.log("서버 등록 결과: " + result);
	}
);
*/	


/*
myCommentClsr.registerReply(
	{bno:bnoValue, rcontent:"JS댓글의 답글등록1", rwriter:"user7", prno: 1},
	function(result) {
		console.log("서버 등록 결과: " + result);
	}	
);
*/


/*
myCommentClsr.getCmtReply(
	{bno:bnoValue, rno:1},
	function(data) {
		console.log(data);
	}
);
*/

		
/*
myCommentClsr.modifyCmtReply(
	{bno:bnoValue, rno:2, rcontent:"JS클로저 답글댓글수정2===="},
	function(result) {
		console.log("수정 결과: " + result);
	}
);
*/


/*
myCommentClsr.removeCmtReply(
	{bno:bnoValue, rno:22},
	function(result) {
		console.log("삭제 결과: " + result);
	},
	function(err) {
		console.log("오류로 인한 삭제 취소:\r" + err);
	}
);
*/


/*
myCommentClsr.modifyFlagCmtReply(
	{bno:bnoValue, rno:20},
	function(result){
		console.log("수정 결과: " + result);		
	}
);
*/

</script> --%>

