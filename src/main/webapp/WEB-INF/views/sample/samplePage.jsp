<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%
/**
 * <pre>
 * @COPYRIGHT (c) 2022 조남훈. All Right Reserved.
 *
 * @File Name        : samplePage.jsp
 * @File path        : /SBAPI_PROJECT_SAMPLE/src/main/webapp/WEB-INF/views/sample/samplePage.jsp
 * @author           : 조남훈
 * @Description      : 샘플 페이지
 * @History          : 2023-01-13
 * @Javascript Path  : /SBAPI_PROJECT_SAMPLE/src/main/webapp/WEB-INF/resources/js/bizjs/sample/samplePage.js
 * </pre>
 **/
%>
<%@ include file="/WEB-INF/views/common/inc_header_view.jsp" %>
<%
%>
<script type="text/javascript">
$(document).ready(function() {
});
</script>

<script type="text/javascript" src="<%=_CONTEXT_PATH_%>/js/bizjs/<c:out value="${jspath}" />.js?<%=_TRX_TM_%>"></script>

<!-- S: content --------------------------------------------------------------------------------------------------->

<!-- S: Filter -->
<div class="container p5">

	<h2>Hello world</h2>
	
	<h5 class="mt20">요청부</h5>
	<div id="requestArea" class="mt5">
		◎ API URI : <input id="apiUri" name="apiUri" class="w500" value="" placeholder="/user/getInfo" />
		<br/>
		◎ API Header Field : 
		<ul id="headerInputUl" class="mt5">
			<li>
				<span>[1]</span>
				KEY: <input name="inputKey" class="inputKey w200" value="apiKey" readonly />
				VALUE: <input name="inputValue" class="inputValue w300" value="1q2w3e4r" />
				<a href="javascript:void(0);" class="btn btn-blue plus">+</a> <a href="javascript:void(0);" class="btn btn-gray minus hide">-</a>
			</li>
		</ul>
		<br/>
		◎ API Body Field : 
		<ul id="bodyInputUl" class="mt5">
			<li>
				<span>[1]</span>
				KEY: <input name="inputKey" class="inputKey w200" value="" />
				VALUE: <input name="inputValue" class="inputValue w300" value="" />
				<a href="javascript:void(0);" class="btn btn-blue plus">+</a> <a href="javascript:void(0);" class="btn btn-gray minus hide">-</a>
			</li>
		</ul>
		<a href="javascript:void(0);" id="btnSearch" class="btn">요청값 전송</a>
		<br/>
		<textarea id="requestDataArea" class="w800" rows="30" readonly></textarea>
	</div>
	
	<h5 class="mt20">응답부</h5>
	<div id="responseArea" class="mt5">
		<textarea id="responseDataArea" class="w800" rows="30" readonly></textarea>
	</div>
	
</div>

<!-- E: content --------------------------------------------------------------------------------------------------->

<%@ include file="/WEB-INF/views/common/inc_footer_view.jsp" %>