<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.eventCoupon.model.*"%>

<%
// �q�ШD����o EventCouponVO ����
// ��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
EventCouponVO eventCouponVO = (EventCouponVO) request.getAttribute("eventCouponVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�u�f��ק� - update_eventCoupon_input.jsp</title>
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

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
	padding: 1px;
}
</style>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px;
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�u�f��ק� - update_eventCoupon_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/test.jpg"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~�H���C�� --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<%
	if (eventCouponVO != null) {
	%>
	<FORM METHOD="post" ACTION="eventCoupon.do" name="form1">
		<input type="hidden" name="event_coupon_no"
			value="<%=eventCouponVO.getEvent_coupon_no()%>">
		<table>
			<tr>
				<td>�����u�f��s��:<font color=red><b>*</b></font></td>
				<td><%=eventCouponVO.getEvent_coupon_no()%></td>
			</tr>
			<!-- �t�ӽs����ܡA�ϥ� JSP useBean ���� -->
			<jsp:useBean id="hostSvc" scope="page"
				class="com.host.model.HostService" />
			<tr>
				<td>�t�ӽs��:<font color=red><b>*</b></font></td>
				<td><select size="1" name="host_no">
						<c:forEach var="hostVO" items="${hostSvc.all}">
							<option value="${hostVO.host_no}"
								${eventCouponVO.host_no == hostVO.host_no ? "selected" : ""}>${hostVO.host_name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<!-- ��L���A�p�u�f��W�١B�N�X�B�ϥέ�� -->
			<tr>
				<td>�����u�f��W��:</td>
				<td><input type="TEXT" name="event_coupon_name"
					value="<%=eventCouponVO.getEvent_coupon_name()%>" size="45" /></td>
			</tr>
			<tr>
				<td>�u�f��N�X:</td>
				<td><input type="TEXT" name="coupon_code"
					value="<%=eventCouponVO.getCoupon_code()%>" size="45" /></td>
			</tr>
			<tr>
				<td>�ϥΦ��ƭ���:</td>
				<td><input type="number" name="usage_limit" value="<%=(eventCouponVO != null) ? eventCouponVO.getUsage_limit().toString() : ""%>"></td>
			</tr>
			<tr>
				<td>�Ѿl����:</td>
				<td><input type="number" name="remaining_times"
					value="<%=eventCouponVO.getRemaining_times()%>" size="45" /></td>
			</tr>
			<tr>
				<td>���F���O���B:</td>
				<td><input type="number" name="min_spend"
					value="<%=eventCouponVO.getMin_spend()%>" size="45" /></td>
			</tr>
			<tr>
				<td>�馩���B:</td>
				<td><input type="number" name="event_coupon_discount"
					value="<%=eventCouponVO.getEvent_coupon_discount()%>" size="45" /></td>
			</tr>
			<tr>
				<td>�_�l�ϥήɶ�:</td>
				<td><input name="start_date" id="start_date" type="text"
					value="<%=eventCouponVO.getStart_date() != null ? eventCouponVO.getStart_date().toString() : ""%>" /></td>
			</tr>
			<tr>
				<td>�����ϥήɶ�:</td>
				<td><input name="end_date" id="end_date" type="text"
					value="<%=eventCouponVO.getEnd_date() != null ? eventCouponVO.getEnd_date().toString() : ""%>" /></td>
			</tr>
			<tr>
				<td>�����u�f�骬�A:</td>
				<td><select name="event_coupon_status">
						<option value="0"
							<%=eventCouponVO.getEvent_coupon_status() == 0 ? "selected" : ""%>>�u�f�|���}�l</option>
						<option value="1"
							<%=eventCouponVO.getEvent_coupon_status() == 1 ? "selected" : ""%>>�u�f��</option>
						<option value="2"
							<%=eventCouponVO.getEvent_coupon_status() == 2 ? "selected" : ""%>>�u�f�w����</option>
				</select></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="update"> <input
			type="submit" value="�e�X�ק�">
	</FORM>
	<%
	} else {
	%>
	<p>���~�G�u�f��H���L�k�[���C</p>
	<%
	}
	%>

	<script>
		$(document).ready(
				function() {
					$.datetimepicker.setLocale('zh'); // �]�m�y��������

					$('#start_date').datetimepicker({
						format : 'Y-m-d H:i:s',
						minDate: new Date(),
						onChangeDateTime : function(dp, $input) {
							$('#end_date').datetimepicker('setOptions', {
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
									});
								},
								timepicker : true,
								step : 30
							});
				});
	</script>

</body>
</html>
