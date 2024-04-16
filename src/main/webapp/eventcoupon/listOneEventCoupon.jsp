<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.eventCoupon.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.*"%>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
    EventCouponVO eventCouponVO = (EventCouponVO) request.getAttribute("eventCouponVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String formattedStartDate = "";
    if (eventCouponVO != null && eventCouponVO.getStart_date() != null) {
        formattedStartDate = dateFormat.format(eventCouponVO.getStart_date());
    }

    String formattedEndDate = "";
    if (eventCouponVO != null && eventCouponVO.getEnd_date() != null) {
        formattedEndDate = dateFormat.format(eventCouponVO.getEnd_date());
    }
%>

<html>
<head>
<title>�u�f���� - listOneEventCoupon.jsp</title>

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
    width: 121.5%; /* Adjusted to 100% for full width */
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
    white-space: nowrap; /* Prevent text from wrapping */
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
    <tr><td>
         <h3>�u�f���� - listOneEventCoupon.jsp</h3>
         <h4><a href="select_page.jsp"><img src="images/test.jpg" width="100" height="50" border="0">�^����</a></h4>
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
    </tr>
    <tr>
        <td><%= eventCouponVO.getEvent_coupon_no() %></td>
        <td><%= eventCouponVO.getHost_no() %></td>
        <td><%= eventCouponVO.getEvent_coupon_name() %></td>
        <td><%= eventCouponVO.getCoupon_code() %></td>
        <td><%= eventCouponVO.getUsage_limit() %></td>
        <td><%= eventCouponVO.getRemaining_times() %></td>
        <td><%= eventCouponVO.getMin_spend() %></td>
        <td><%= eventCouponVO.getEvent_coupon_discount() %></td>
        <td><%= formattedStartDate %></td>
    	<td><%= formattedEndDate %></td>
        <td><%= eventCouponVO.getEvent_coupon_status() %></td>
    </tr>
</table>

</body>
</html>
