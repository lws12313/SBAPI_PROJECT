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
<div class="container">
	Hello world
</div>

<!-- E: content --------------------------------------------------------------------------------------------------->

<%@ include file="/WEB-INF/views/common/inc_footer_view.jsp" %>