/**
 * <pre>
 * @COPYRIGHT (c) 2022 조남훈. All Right Reserved.
 *
 * @File Name      : samplePage.js
 * @File path      : /SBAPI_PROJECT_SAMPLE/src/main/resources/static/js/bizjs/sample/samplePage.js
 * @author         : 조남훈
 * @Description    : 샘플 페이지
 * @History        : 2023-01-13
 * </pre>
 **/
 
$(function() {
    
	/***************************************************************************
	* ready 함수
	**************************************************************************/
	/* 페이지 로드시 호출 */
	_thisPage.onload();
	
	/***************************************************************************
	 * 이벤트
	 **************************************************************************/
    /* 검색 버튼 클릭시 */
    $("#btnSearch").on("click", function(){
		_thisPage.inqData();
    });
    
    /* API Header Field 버튼 클릭시 (+, -) */
    $(document).on("click", "#headerInputUl > li > .plus", function() {
		var cloneLine = $("#headerInputUl > li:eq(0)").clone();
		cloneLine.find("span").text("["+ ($("#headerInputUl > li").length+1) + "]");
		cloneLine.find("input[name=inputKey]").val("");
		cloneLine.find("input[name=inputKey]").removeAttr("readonly");
		cloneLine.find("input[name=inputValue]").val("");
		cloneLine.find(".minus").removeClass("hide");
		$("#headerInputUl").append(cloneLine);
	});
	$(document).on("click", "#headerInputUl > li > .minus", function() {
		if($("#headerInputUl > li").length > 1) {
			$(this).parent().remove();
			$("#headerInputUl > li").find("span").each(function(i) {
				$(this).text("["+ (i+1) + "]");
			});
		}
	});
	
	/* API Body Field 버튼 클릭시 (+, -) */
    $(document).on("click", "#bodyInputUl > li > .plus", function() {
		var cloneLine = $("#bodyInputUl > li:eq(0)").clone();
		cloneLine.find("span").text("["+ ($("#bodyInputUl > li").length+1) + "]");
		cloneLine.find("input[name=inputKey]").val("");
		cloneLine.find("input[name=inputValue]").val("");
		cloneLine.find(".minus").removeClass("hide");
		$("#bodyInputUl").append(cloneLine);
	});
	$(document).on("click", "#bodyInputUl > li > .minus", function() {
		if($("#bodyInputUl > li").length > 1) {
			$(this).parent().remove();
			$("#bodyInputUl > li").find("span").each(function(i) {
				$(this).text("["+ (i+1) + "]");
			});
		}
	});
    
});

var _thisPage = {
	
	/* 페이지 상수 정의 */
	
	/* 화면셋팅 변수 정의 */
	
	/* 페이지 변수 정의 */
	apiUri: ""			//API URI 입력값
	, apiHeader: {}		//API Header 입력값
	, apiBody: {}		//API Body 입력갑
	
	/* 페이지 로드 */
	, onload : function() {
    	
	}
	
	/* 데이터 조회 */
	, inqData: function() {
		if(!_thisPage.inqDataValidChk()) return false;
		
		//파라미터 셋팅
		var reqUri = _thisPage.apiUri;
		var reqHeader = _thisPage.apiHeader;
		var reqParam = _thisPage.apiBody;
		$("#requestDataArea").val("HEADER : \n" + JSON.stringify(reqHeader) + "\n\nBODY : \n" + JSON.stringify(reqParam));
		
		//Ajax 호출 - 커스텀 API 호출 (API)
		comAjax(reqUri, reqParam
			, function(data){
				//조회결과 출력
				$("#responseDataArea").val(JSON.stringify(data));
				
			}
			, function(fData){
				//조회결과 출력
				$("#responseDataArea").val(JSON.stringify(fData));
			}
			, true
			, reqHeader
		);
	}
	/* 데이터 조회 파라미터 유효성 체크 및 셋팅 */
	, inqDataValidChk: function() {
		
		//파라미터 변수 셋팅
		var apiUri = $("#apiUri").val();
		
		//파리미터 유효성 검사
		if(apiUri == null || apiUri == "") {
			alert("API URI 는 필수 입력 항목입니다.");
			return false;
		}
		
		//파라미터 셋팅
		//1. API URI
		_thisPage.apiUri = "";
		if(apiUri.substring(0, 1) != "/") {
			apiUri = "/"+$("#apiUri").val();
			$("#apiUri").val(apiUri);
		}
		_thisPage.apiUri = apiUri;
		
		//2. API HEADER FIELD
		_thisPage.apiHeader = {};
		if($("#headerInputUl > li").length > 0) {
			$("#headerInputUl > li").each(function() {
				if($(this).find("input[name='inputKey']").val() != "") {
					_thisPage.apiHeader[$(this).find("input[name='inputKey']").val()] = $(this).find("input[name='inputValue']").val();
				}
			});
		}
		
		//3. API BODY FIELD
		_thisPage.apiBody = {};
		if($("#bodyInputUl > li").length > 0) {
			$("#bodyInputUl > li").each(function() {
				if($(this).find("input[name='inputKey']").val() != "") {
					_thisPage.apiBody[$(this).find("input[name='inputKey']").val()] = $(this).find("input[name='inputValue']").val();
				}
			});
		}
		
		return true;
		
	}
	
}