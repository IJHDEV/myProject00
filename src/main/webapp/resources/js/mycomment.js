/**
 * mycomment.js: 댓글/답글 데이터 처리용 Ajax Closer Module */
 
//alert("댓글처리 클로저 모듈 실행됨========");

var myCommentClsr = (function(){

	function getCmtList(myParam, callback, error) {
		var bno = myParam.bno;
        var pageNum = myParam.pageNum || 1;
		
		$.ajax({
			type: "get",
			url: "/mypro00/replies/" + bno + "/page=" + pageNum,
			dataType: "json",
			success: function(myReplyPagingCreator, status, xhr){
				if(callback) {
					callback(myReplyPagingCreator);
				}
			},
			error: function(xhr, status, err) {
				if(error){
					error(err);
				}
			}
		}); // ajax-end
	} // getCmtList-end
	
	
	function registerCmt(comment, callback, error) {
		var bno = comment.bno;
		
		$.ajax({
			type: "post",
			url: "/mypro00/replies/" + bno + "/new",
			data: JSON.stringify(comment),
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			success: function(result, status, xhr){
				if(callback) {
					callback(result);
				}
			},
			error: function(xhr, status, err){
				if(error) {
					error(err);
				}
			}
		}); // ajax-end
	} // registerCmt-end
	
	
	function registerReply(reply, callback, error) {
		var bno = reply.bno;
		var prno = reply.prno;
		
		$.ajax({
			type: "post",
			url: "/mypro00/replies/" + bno + "/" + prno + "/new",
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			success: function(result, status, xhr){
				if(callback) {
					callback(result);
				}
			},
			error: function(xhr, status, err){
				if(error) {
					error(err);
				}
			}
		}); // ajax-end
	} // registerReply-end

	
	function getCmtReply(bnoAndRno, callback, error){
		var bno = bnoAndRno.bno;
		var rno = bnoAndRno.rno;
		
		$.ajax({
			type: "get",
			url: "/mypro00/replies/" + bno + "/" + rno,
			dataType: "json",
			success: function(data, status, xhr){
				if (callback) {
					callback(data);
				}
			},
			error: function(xhr, status, err){
				if (error) {
					error(err);
				}
			}
		}); // ajax-end
	} // getCmtReply-end


	function modifyCmtReply(cmtReply, callback, error){
		var bno = cmtReply.bno;
		var rno = cmtReply.rno;
		
		$.ajax({
			type: "put",
			url: "/mypro00/replies/" + bno + "/" + rno,
			data: JSON.stringify(cmtReply),
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			success: function(result, status, xhr){
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, err){
				if (error){
					error(err);
				}
			}
		}); //ajax-end
	} // modifyCmtReply-end
	
	
	function removeCmtReply(cmtReply, callback, error){
		var bno = cmtReply.bno;
		var rno = cmtReply.rno;
		
		$.ajax({
			type: "delete",
			url: "/mypro00/replies/" + bno + "/" + rno,
			dataType: "text",
			success: function(result, status, xhr){
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, err){
				if (error) {
					error(err);
				}
			}		
		}); // ajax-end
	} // removeCmtReply-end
	
	
	function modifyFlagCmtReply(cmtReply, callback, error){
 		var bno = cmtReply.bno;
 		var rno = cmtReply.rno;
 		
 		$.ajax({
 			type: "patch",
 			url: "/mypro00/replies/" + bno + "/" + rno,
// 			data: JSON.stringify({rwriter: cmtReply.rwriter}),
 			data: JSON.stringify(cmtReply),
			contentType: "application/json; charset=utf-8",
 			dataType: "text",
 			success: function(result, status, xhr){
 				if(callback){
 					callback(result);
 				}
 			},
 			error: function(xhr, status, err){
 				if(error){
 					error(err);
 				}
 			}
 		}); // ajax-end
 	} // modifyFlagCmtReply-end
	
	
    // 날짜 시간 표시형식 설정
    function showDatetime(datetimeValue) {
        var dateObj = new Date(datetimeValue);
//      console.log("dateObj: " + dateObj.toString());
        var str = "";

        var yyyy = dateObj.getFullYear();
        var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
        var dd = dateObj.getDate();
        var hh = dateObj.getHours();
        var mi = dateObj.getMinutes();
        var ss = dateObj.getSeconds();

        str = [ yyyy, '/',
               (mm > 9 ? '' : '0') + mm, '/',
               (dd > 9 ? '' : '0') + dd, ' ',
               (hh > 9 ? '' : '0') + hh, ':',
               (mi > 9 ? '' : '0') + mi, ':',
               (ss > 9 ? '' : '0') + ss ].join('');
        
        return str;
    }
    
    
    function notifyRegisterTime(datetimeValue) {
    	var nowDate = new Date().getTime();
    	var dateObj = new Date(datetimeValue).getTime();
    	var timeValue = nowDate - dateObj;
    	
    	//시 분 초 밀리세컨드 24 * 60 * 60 * 1000 = 86400000
    	var miValue = Math.floor(timeValue / 60000); // 분
    	var hhValue = Math.floor(timeValue / 3600000); // 시
    	var ddValue = Math.floor(timeValue / 86400000); // 일
    	var mmValue = Math.floor(timeValue / 2592000000); // 달
    	var yyValue = Math.floor(timeValue / 31104000000); // 년
    	
    	var str = "";
    	
    	if (miValue < 1) {
    		str = "방금 전";
    	} else if (miValue < 60) {
    		str = miValue + "분 전";
    	} else if (hhValue < 24) {
    		str = hhValue + "시간 전";
    	} else if (ddValue < 30) {
    		str = ddValue + "일 전";
    	} else if (mmValue < 12) {
    		str = mmValue + "달 전";
    	} else {
    		str = yyValue + "년 전";
    	}
    	return str;
    }
    
    

	//myCommentClsr
	return {
		getCmtList: getCmtList, //목록
		registerCmt: registerCmt, //댓글등록
		registerReply: registerReply, //답글등록
		getCmtReply: getCmtReply, //특정 댓글/답글 조회
		modifyCmtReply: modifyCmtReply, //특정 댓글/답글 수정
		removeCmtReply: removeCmtReply, //특정 댓글/답글 삭제
		modifyFlagCmtReply: modifyFlagCmtReply, //특정 댓글/답글 삭제 요청(*)
        showDatetime: showDatetime,
        notifyRegisterTime: notifyRegisterTime
	};
	
})();