<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.eventCoupon.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
EventCouponService eventCouponServiceSvc = new EventCouponService();
    List<EventCouponVO> list = eventCouponServiceSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有優惠券資料 - listAllEventCoupon.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有優惠券資料 - listAllEventCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/test.jpg" width="200" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動優惠券編號</th>
		<th>廠商編號</th>
		<th>活動優惠券名稱</th>
		<th>優惠券代碼</th>
		<th>使用次數限制</th>
		<th>剩餘次數</th>
		<th>應達消費金額</th>
		<th>折扣金額</th>
		<th>起始使用時間</th>
		<th>結束使用時間</th>
		<th>活動優惠券狀態</th>
		<th>修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="eventCouponVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			
			<td>${eventCouponVO.event_coupon_no}</td>
			<td>${eventCouponVO.host_no}</td>
			<td>${eventCouponVO.event_coupon_name}</td>
			<td>${eventCouponVO.coupon_code}</td>
			<td>${eventCouponVO.usage_limit}</td>
			<td>${eventCouponVO.remaining_times}</td> 
			<td>${eventCouponVO.min_spend}</td>
			<td>${eventCouponVO.event_coupon_discount}</td>
			<fmt:formatDate value="${eventCouponVO.start_date}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedStartDate" />
            <fmt:formatDate value="${eventCouponVO.end_date}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedEndDate" />
            <td>${formattedStartDate}</td>
            <td>${formattedEndDate}</td>
			<td>${eventCouponVO.event_coupon_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/eventcoupon/eventCoupon.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="event_coupon_no"  value="${eventCouponVO.event_coupon_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
		
		
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>