<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.eventCoupon.model.*"%>

<%
// 從請求中獲得 EventCouponVO 物件
// 見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
EventCouponVO eventCouponVO = (EventCouponVO) request.getAttribute("eventCouponVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>優惠券新增 - add_eventCoupon.jsp</title>

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

table, th, td {
	border: 0px solid #CCCCFF;
	padding: 1px;
}

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}
</style>

<!-- Using CDN for jQuery and datetimepicker -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>優惠券新增 - add_eventCoupon.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/test.jpg"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="eventCoupon.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<jsp:useBean id="hostSvc" scope="page"
				class="com.host.model.HostService" />
			<tr>
				<td>廠商名稱:<font color=red><b>*</b></font></td>
				<td><select size="1" name="host_no">
						<c:forEach var="hostVO" items="${hostSvc.all}">
							<option value="${hostVO.host_no}"
								${(eventCouponVO.host_no==hostVO.host_no)?'selected':'' }>${hostVO.host_name}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>活動優惠券名稱:</td>
				<td><input type="TEXT" name="event_coupon_name"
					value="<%=(eventCouponVO == null) ? "" : eventCouponVO.getEvent_coupon_name()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>優惠券代碼:</td>
				<td><input type="TEXT" name="coupon_code"
					value="<%=(eventCouponVO == null) ? "" : eventCouponVO.getCoupon_code()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>使用次數限制:</td>
				<td><input type="TEXT" name="usage_limit"
					value="<%=(eventCouponVO == null) ? "0" : eventCouponVO.getUsage_limit()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>剩餘次數:</td>
				<td><input type="TEXT" name="remaining_times"
					value="<%=(eventCouponVO == null) ? "0" : eventCouponVO.getRemaining_times()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>應達消費金額:</td>
				<td><input type="TEXT" name="min_spend"
					value="<%=(eventCouponVO == null) ? "0" : eventCouponVO.getMin_spend()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>折扣金額:</td>
				<td><input type="TEXT" name="event_coupon_discount"
					value="<%=(eventCouponVO == null) ? "0" : eventCouponVO.getEvent_coupon_discount()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>起始使用時間:</td>
				<td><input name="start_date" id="start_date" type="text"
					value="<%=(eventCouponVO == null) ? "" : eventCouponVO.getStart_date()%>"></td>
			</tr>
			<tr>
				<td>結束使用時間:</td>
				<td><input name="end_date" id="end_date" type="text"
					value="<%=(eventCouponVO == null) ? "" : eventCouponVO.getEnd_date()%>"></td>
			</tr>
			<tr>
				<td>活動優惠券狀態:</td>
				<td><select name="event_coupon_status">
						<option value="0"
							<%=(eventCouponVO != null && eventCouponVO.getEvent_coupon_status() == 0) ? "selected" : ""%>>優惠尚未開始</option>
						<option value="1"
							<%=(eventCouponVO != null && eventCouponVO.getEvent_coupon_status() == 1) ? "selected" : ""%>>優惠中</option>
						<option value="2"
							<%=(eventCouponVO != null && eventCouponVO.getEvent_coupon_status() == 2) ? "selected" : ""%>>優惠已結束</option>
				</select></td>
			</tr>
			<tr>
				<td>圖片上傳:</td>
				<td><input type="file" name="coupon_image" accept="image/*" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

	<script>
		$(document).ready(
				function() {
					$.datetimepicker.setLocale('zh'); // 設置語言為中文

					$('#start_date').datetimepicker(
							{
								format : 'Y-m-d H:i:s',
								minDate : new Date(),
								onShow : function(ct) {
									this.setOptions({
										maxDate : $('#end_date').val() ? $(
												'#end_date').val() : false
									})
								},
								onChangeDateTime : function(dp, $input) {
									$('#end_date').datetimepicker('setOptions',
											{
												minDate : $input.val()
											});
								},
								timepicker : true,
								step : 30
							});

					$('#end_date').datetimepicker(
							{
								format : 'Y-m-d H:i:s',
								onShow : function(ct) {
									this.setOptions({
										minDate : $('#start_date').val() ? $(
												'#start_date').val() : false
									})
								},
								timepicker : true,
								step : 30
							});
				});
	</script>

</body>
</html>
