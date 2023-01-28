<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%
/**
 * <pre>
 * @COPYRIGHT (c) 2022 조남훈. All Right Reserved.
 *
 * @File Name        : inc_header_act.jsp
 * @File path        : /SBAPI_PROJECT_SAMPLE/src/main/webapp/WEB-INF/views/common/inc_header_act.jsp
 * @author           : 조남훈
 * @Description      : 공통 헤더 액션
 * @History          : 
 * @Javascript Path  : 
 * @JavaScript Url   : 
 * </pre>
 **/
%>
<% /* JAVA 클래스 */ %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.*" %>

<% /* 업무공통 클래스 */ %>
<%@ page import="com.sbapi.project.util.CommUtil"       %>
<%@ page import="com.sbapi.project.util.StrUtil"        %>
<%@ page import="com.sbapi.project.util.FormatUtil"     %>
<%@ page import="com.sbapi.project.sess.SessionManager" %>
<%@ page import="com.sbapi.project.sess.SbapiSession"   %>

<%
/**
 * 페이지 로딩시 처리해야 할 jsp 로직 영역
 */
%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('sbapi.project.profiles.active')" var="_ENV_MD_" />

<%
/**
 * Cache 삭제
 */
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
if("HTTP/1.1".equals(request.getProtocol())) {
    response.setHeader("Cache-Control", "no-cache");
}

/**
 * 공통 전역변수 선언
 */
String _CONTEXT_PATH_     = request.getContextPath();                     //context path
String _TRX_TM_           = FormatUtil.getCurDateTime("yyyyMMddHHmmss");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>SBAPI 샘플</title>
	
	<%
    /**
     * 메타태그 관련 import
     */
    %>
    <meta charset="UTF-8"  />
    <meta http-equiv="Content-Type"    content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport"              content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    
    <%
    /**
     * Favicon 관련 파일 import
     */
    %>
    
	<%
    /**
     * 퍼블리싱 관련 파일 import
     */
    %>
    <link rel="stylesheet" type="text/css" media="all" href="<%=_CONTEXT_PATH_%>/css/style.css?<%=_TRX_TM_%>" />
    
     <%
    /**
     * 공통 스크립트 관련 파일 import ( lib )
     */
    %>
    <script type="text/javascript" src="<%=_CONTEXT_PATH_%>/js/lib/jquery-1.12.4.min.js?<%=_TRX_TM_%>"></script>
    <script type="text/javascript">
    	//jquery를 참조하는 lib.js 연계시 browser.msie 관련 오류를 방지하기 위한 처리 추가
		jQuery.browser = {};
		(function () {
		    jQuery.browser.msie = false;
		    jQuery.browser.version = 0;
		    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
		        jQuery.browser.msie = true;
		        jQuery.browser.version = RegExp.$1;
		    }
		})();
	</script>
	
	<%
    /**
     * 공통 스크립트 관련 파일 import ( common )
     */
    %>
    <script type="text/javascript" src="<%=_CONTEXT_PATH_%>/js/common/sbapi_common.js?<%=_TRX_TM_%>"></script>
    
    <%
    /**
     * 전역변수 처리 스크립트 영역
     */
    %>
    <script type="text/javascript">
	    var _SERVER_TIME_  = "<%=_TRX_TM_%>";
	    var _CONTEXT_PATH_ = "<%=_CONTEXT_PATH_%>";
	    var _ENV_MD_	   = "<c:out value="${_ENV_MD_}" />";
	    
		$(document).ready(function(){
		});
    </script>
	
</head>

<body>