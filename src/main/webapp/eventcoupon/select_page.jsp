<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>優惠券</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>優惠券</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for EventCoupon: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllEventCoupon.jsp'>List</a> all EventCoupon.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="eventCoupon.do" >
        <b>輸入優惠券編號 (如1):</b>
        <input type="text" name="event_coupon_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="eventCouponSvc" scope="page" class="com.eventCoupon.model.EventCouponService" />
   
  <li>
     <FORM METHOD="post" ACTION="eventCoupon.do" >
       <b>選擇優惠券編號:</b>
       <select size="1" name="event_coupon_no">
         <c:forEach var="eventCouponVO" items="${eventCouponSvc.all}" > 
          <option value="${eventCouponVO.event_coupon_no}">${eventCouponVO.event_coupon_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="eventCoupon.do" >
       <b>選擇廠商編號:</b>
       <select size="1" name="host_no">
         <c:forEach var="eventCouponVO" items="${eventCouponSvc.all}" > 
          <option value="${eventCouponVO.host_no}">${eventCouponVO.host_no}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>優惠券管理</h3>

<ul>
  <li><a href='addEventCoupon.jsp'>Add</a> a new EventCoupon.</li>
</ul>

</body>
</html>
