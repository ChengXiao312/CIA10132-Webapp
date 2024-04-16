<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.eventCoupon.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
EventCouponService eventCouponServiceSvc = new EventCouponService();
    List<EventCouponVO> list = eventCouponServiceSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��u�f���� - listAllEventCoupon.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��u�f���� - listAllEventCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/test.jpg" width="200" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�����u�f��s��</th>
		<th>�t�ӽs��</th>
		<th>�����u�f��W��</th>
		<th>�u�f��N�X</th>
		<th>�ϥΦ��ƭ���</th>
		<th>�Ѿl����</th>
		<th>���F���O���B</th>
		<th>�馩���B</th>
		<th>�_�l�ϥήɶ�</th>
		<th>�����ϥήɶ�</th>
		<th>�����u�f�骬�A</th>
		<th>�ק�</th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="event_coupon_no"  value="${eventCouponVO.event_coupon_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
		
		
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>