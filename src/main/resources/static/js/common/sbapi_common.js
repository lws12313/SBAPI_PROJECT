/**
 * <pre>
 * @COPYRIGHT (c) 2022 조남훈. All Right Reserved.
 *
 * @File Name      : sbapi_common.js
 * @File path      : /SBAPI_PROJECT_SAMPLE/src/main/resources/static/js/common/sbapi_common.js
 * @author         : 조남훈
 * @Description    : 업무공통 JS
 * @History        : 2023-01-13
 * </pre>
 **/

/**
 * ajax 호출함수
 * param 
 * ★ url (String)* 	 : 호출할 api url
 * ★ paramObj (Object)* : 파라미터 객체
 * ★ fnSucc (String | Object)* : ajax 성공 후 콜백함수 명 또는 함수
 * fnFail (String | Object) : ajax 실패 후 콜백함수 명 또는 함수
 * bErrMsg (boolean) 	: 에러 메시지 표시 여부 (default: true)
 * ex) 1. comAjax( "TEST_URL"
 * 				, param
    			, function(data){
    				//ajax성공 콜백 함수
    			} 
    			);
      2. comAjax( "TEST_URL"
 * 				, param
    			, "fncSuccCallback"
    			);		
 * */
function comAjax(url, paramObj, fnSucc, fnFail, bErrMsg, headerObj){
	//에러응답시 공통 Alert 처리 여부
	var errMsg = true;
	if(typeof bErrMsg == "boolean"){
		errMsg = bErrMsg;
	}
	
	if(paramObj != null && typeof paramObj == 'object'){	//파라미터 set
		paramObj = JSON.stringify(paramObj);
	}
	
	$("div.loading").show();
	$.ajax({
		type : 'POST',
		url : url,
		data : paramObj,
		contentType: "application/json; charset=utf-8",
		async: true,
		timeout: 20000,
		dataType: "JSON",
		beforeSend: function(xhr) {
			if(headerObj != null) {
				var headerKeys = Object.keys(headerObj);
				for(var i=0; i<headerKeys.length; i++) {
					xhr.setRequestHeader(headerKeys[i], headerObj[headerKeys[i]]);
				}
			}
		},
		success : function(data) {
			if(fnSucc != null && fnSucc != undefined){	//succ callback set
    			var fn ;
				if( typeof callbackFn == "string"){
					fn = window[fnSucc];
				} else {
					fn = fnSucc;
				}
				if(typeof fn === "function"){ 
					fn(data);
				}
    		}
		},
		error : function(fData) {
			console.log(fData);
			if((fData.responseJSON != null && fData.responseJSON.errorCode == "SESS_LOGIN_EXPIRED") && ComUtil.getMobileOs() == "NONE"){
				alert("세션이종료되었습니다.");
				return false;
			}else{
	    		if(errMsg){
					if((fData.responseJSON != null && fData.responseJSON.errorCode != null) && fData.responseJSON.errorMessage != null) {
						errMsg = "[" + fData.responseJSON.errorCode + "] " + fData.responseJSON.errorMessage;
					}else {
						errMsg = "통신중 오류가 발생했습니다. 시스템 관리자에게 문의하세요.";
					}
					alert(errMsg);
					if(fnFail != null && fnFail != undefined){	//fail callback set
		    			var fn ;
						if( typeof callbackFn == "string"){
							fn = window[fnFail];
						} else {
							fn = fnFail;
						}
						if(typeof fn === "function"){ 
							if(fData.responseJSON != null) {
								fn(fData.responseJSON);
							}else {
								fn(fData);
							}
						}
		    		}
	    			
	    		}else {
					if(fnFail != null && fnFail != undefined){	//fail callback set
		    			var fn ;
						if( typeof callbackFn == "string"){
							fn = window[fnFail];
						} else {
							fn = fnFail;
						}
						if(typeof fn === "function"){ 
							if(fData.responseJSON != null) {
								fn(fData.responseJSON);
							}else {
								fn(fData);
							}
						}
		    		}
				}
			}
			
		},
		complete: function() {
			$("div.loading").hide();
		}
	});
	
}

(function($){
	
	/**
	 * serializeObject
	 * ajax 전송시 폽 요소 Object로 변환하여 전송
	 * */
	$.fn.serializeObject = function() {
		var result = {}
		var extend = function(i, element) {
			try{
			var node = result[element.name]
			if ("undefined" !== typeof node && node !== null) {
				if ($.isArray(node)) {
					node.push(element.value)
				} else {
					result[element.name] = [ node, element.value ]
				}
			} else {
				result[element.name] = element.value
			}
			}catch(e){
				log(e);
			}
		}

		$.each(this.serializeArray(), extend)
		return result
	}
	
})(jQuery);


$.extend({
	
	/**
	 * 브라우저 쿠키 데이터 생성
	 * @param cookieName : 쿠키 식별자
	 * @param value : 쿠키 값
	 * @param exdays : 쿠키 유효기간(일자)
	 * ex) $.setCookie("user_id", "master", 7);
	 */
	setCookie: function(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value) + ((exdate==null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "= " + cookieValue;
	}
	
	/**
	 * 브라우저 쿠키 데이터 조회
	 * @param cookieName : 쿠키 식별자
	 * ex) $.getCookie("user_id");
	 */
	, getCookie: function(cookieName) {
		cookieName = cookieName + "=";
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = "";
		if(start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(";", start);
			if(end == -1) end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
	
	/**
	 * 브라우저 쿠키 데이터 삭제
	 * @param cookieName : 쿠키 식별자
	 * ex) $.deleteCookie("user_id");
	 */
	, deleteCookie: function(cookieName, value, exdays) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	
});