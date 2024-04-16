<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.eventCoupon.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    EventCouponVO eventCouponVO = (EventCouponVO) request.getAttribute("eventCouponVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
<title>優惠券資料 - listOneEventCoupon.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
    <tr><td>
         <h3>優惠券資料 - listOneEventCoupon.jsp</h3>
         <h4><a href="select_page.jsp"><img src="images/test.jpg" width="100" height="50" border="0">回首頁</a></h4>
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
