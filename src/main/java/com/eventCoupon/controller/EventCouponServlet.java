package com.eventCoupon.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eventCoupon.model.EventCouponService;
import com.eventCoupon.model.EventCouponVO;

public class EventCouponServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("event_coupon_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入活動優惠券編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eventcoupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer event_coupon_no = null;
			try {
				event_coupon_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("活動優惠券編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eventcoupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			EventCouponService eventCouponSvc = new EventCouponService();
			EventCouponVO eventCouponVO = eventCouponSvc.getOneEventCoupon(event_coupon_no);
			if (eventCouponVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/eventcoupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			if (eventCouponVO == null) {
				errorMsgs.add("活動優惠券信息未找到");
				RequestDispatcher failureView = req.getRequestDispatcher("/eventcoupon/select_page.jsp");
				failureView.forward(req, res);
				return;
			} else {
				req.setAttribute("eventCouponVO", eventCouponVO);
				String url = (action.equals("getOne_For_Display") ? "/eventcoupon/listOneEventCoupon.jsp"
						: "/eventcoupon/update_EventCoupon_input.jsp");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("eventCouponVO", eventCouponVO); // 資料庫取出的eventCouponVO物件,存入req
			String url = "/eventcoupon/listOneEventCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEventCoupon.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEventCoupon.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
//			Integer event_coupon_no = Integer.valueOf(req.getParameter("event_coupon_no"));

			Integer event_coupon_no = null;
			try {
				event_coupon_no = Integer.valueOf(req.getParameter("event_coupon_no"));
			} catch (NumberFormatException e) {
				errorMsgs.add("活動優惠券編號格式不正確");
				RequestDispatcher failureView = req.getRequestDispatcher("/update_EventCoupon_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始查詢資料 ****************************************/
			EventCouponService eventCouponSvc = new EventCouponService();
			EventCouponVO eventCouponVO = eventCouponSvc.getOneEventCoupon(event_coupon_no);

			if (eventCouponVO == null) {
				errorMsgs.add("查無數據或活動優惠券編號不正確");
				RequestDispatcher failureView = req.getRequestDispatcher("/errorPage.jsp"); // 指向顯示錯誤的頁面
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("eventCouponVO", eventCouponVO); // 資料庫取出的eventCouponVO物件,存入req
			String url = "/eventcoupon/update_EventCoupon_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_EventCoupon_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_EventCoupon_input.jsp的請求

			System.out.println("Received usage limit: " + req.getParameter("usage_limit"));

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer event_coupon_no = Integer.valueOf(req.getParameter("event_coupon_no").trim());

			String eventCouponNoStr = req.getParameter("event_coupon_no");
			Integer event_coupon_no = null;
			if (eventCouponNoStr == null || eventCouponNoStr.trim().isEmpty()) {
				errorMsgs.add("活動優惠券編號不能為空");
				RequestDispatcher failureView = req.getRequestDispatcher("/eventcoupon/update_EventCoupon_input.jsp");
				failureView.forward(req, res);
				return; // 中斷
			} else {
				event_coupon_no = Integer.valueOf(eventCouponNoStr.trim());
			}

			Integer host_no = Integer.valueOf(req.getParameter("host_no").trim());

			String event_coupon_name = req.getParameter("event_coupon_name");
			String event_coupon_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
			if (event_coupon_name == null || event_coupon_name.trim().length() == 0) {
				errorMsgs.add("活動優惠券名稱: 請勿空白");
			} else if (!event_coupon_name.trim().matches(event_coupon_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動優惠券名稱: 只能是中、英文字母、數字, 且長度必需在2到10之間");
			}

			String coupon_code = req.getParameter("coupon_code").trim();
			String coupon_codeReg = "^[(a-zA-Z0-9)]{2,10}$";
			if (coupon_code == null || coupon_code.trim().length() == 0) {
				errorMsgs.add("優惠券代碼: 請勿空白");
			} else if (!coupon_code.trim().matches(coupon_codeReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠券代碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
			}

			Integer usage_limit = Integer.valueOf(req.getParameter("usage_limit").trim());
			if (usage_limit == null) {
				errorMsgs.add("使用次數限制: 請勿空白");
			} else if (usage_limit == 0) {
				errorMsgs.add("使用次數限制不可為0");
			}

			Integer remaining_times = Integer.valueOf(req.getParameter("remaining_times").trim());
			if (remaining_times == null) {
				errorMsgs.add("剩餘次數: 請勿空白");
			} else if (remaining_times == 0) {
				errorMsgs.add("剩餘次數不可為0");
			}

			Integer min_spend = Integer.valueOf(req.getParameter("min_spend").trim());
			if (min_spend == null) {
				errorMsgs.add("應達消費金額: 請勿空白");
			} else if (min_spend == 0) {
				errorMsgs.add("應達消費金額不可為0");
			}

			Integer event_coupon_discount = Integer.valueOf(req.getParameter("event_coupon_discount").trim());
			if (event_coupon_discount == null) {
				errorMsgs.add("折扣金額: 請勿空白");
			} else if (event_coupon_discount == 0) {
				errorMsgs.add("折扣金額不可為0");
			}

			java.sql.Timestamp start_date = null;
			try {
				start_date = java.sql.Timestamp.valueOf(req.getParameter("start_date").trim());
			} catch (IllegalArgumentException e) {
				start_date = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入起始使用時間!");
			}

			java.sql.Timestamp end_date = null;
			try {
				end_date = java.sql.Timestamp.valueOf(req.getParameter("end_date").trim());
			} catch (IllegalArgumentException e) {
				end_date = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入結束使用時間!");
			}

			Integer event_coupon_status = null;
			try {
			    event_coupon_status = Integer.valueOf(req.getParameter("event_coupon_status").trim());
			    // 可以添加檢查 event_coupon_status 是否在合理範圍內，如介於特定的最小和最大值之間
			} catch (NumberFormatException e) {
			    errorMsgs.add("活動優惠券狀態格式不正確或未填寫");
			}
			
			System.out.println("Updating with: ");
		    System.out.println("event_coupon_no: " + event_coupon_no);
		    System.out.println("host_no: " + host_no);
		    System.out.println("event_coupon_name: " + event_coupon_name);
		    System.out.println("coupon_code: " + coupon_code);
		    System.out.println("usage_limit: " + usage_limit);
		    System.out.println("remaining_times: " + remaining_times);
		    System.out.println("min_spend: " + min_spend);
		    System.out.println("event_coupon_discount: " + event_coupon_discount);
		    System.out.println("start_date: " + start_date);
		    System.out.println("end_date: " + end_date);
		    System.out.println("event_coupon_status: " + event_coupon_status);

			EventCouponVO eventCouponVO = new EventCouponVO();

			eventCouponVO.setEvent_coupon_no(event_coupon_no);
			eventCouponVO.setHost_no(host_no);
			eventCouponVO.setEvent_coupon_name(event_coupon_name);
			eventCouponVO.setCoupon_code(coupon_code);
			eventCouponVO.setUsage_limit(usage_limit);
			eventCouponVO.setRemaining_times(remaining_times);
			eventCouponVO.setMin_spend(min_spend);
			eventCouponVO.setEvent_coupon_discount(event_coupon_discount);
			eventCouponVO.setStart_date(start_date);
			eventCouponVO.setEnd_date(end_date);
			eventCouponVO.setEvent_coupon_status(event_coupon_status);
			
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("eventCouponVO", eventCouponVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/eventcoupon/update_EventCoupon_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			EventCouponService eventCouponSvc = new EventCouponService();
			eventCouponVO = eventCouponSvc.updateEventCoupon(event_coupon_no, host_no, event_coupon_name, coupon_code,
					usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date,
					event_coupon_status);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("eventCouponVO", eventCouponVO); // 資料庫update成功後,正確的的eventCouponVO物件,存入req
			String url = "/eventcoupon/listOneEventCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEventCoupon.jsp
			successView.forward(req, res);

		}

		if ("insert".equals(action)) { // 來自addEventCoupon.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer host_no = Integer.valueOf(req.getParameter("host_no").trim());

			String event_coupon_name = req.getParameter("event_coupon_name");
			String event_coupon_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,100}$";
			if (event_coupon_name == null || event_coupon_name.trim().length() == 0) {
				errorMsgs.add("活動優惠券名稱: 請勿空白");
			} else if (!event_coupon_name.trim().matches(event_coupon_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動優惠券名稱: 只能是中、英文字母、數字 , 且長度必需在2到100之間");
			}

			String coupon_code = req.getParameter("coupon_code").trim();
			String coupon_codeReg = "^[(a-zA-Z0-9)]{2,10}$";
			if (coupon_code == null || coupon_code.trim().length() == 0) {
				errorMsgs.add("優惠券代碼: 請勿空白");
			} else if (!coupon_code.trim().matches(coupon_codeReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠券代碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
			}

			Integer usage_limit = Integer.valueOf(req.getParameter("usage_limit").trim());
			if (usage_limit == null) {
				errorMsgs.add("使用次數限制: 請勿空白");
			} else if (usage_limit == 0) {
				errorMsgs.add("使用次數限制不可為0");
			}

			Integer remaining_times = Integer.valueOf(req.getParameter("remaining_times").trim());
			if (remaining_times == null) {
				errorMsgs.add("剩餘次數: 請勿空白");
			} else if (remaining_times == 0) {
				errorMsgs.add("剩餘次數不可為0");
			}

			Integer min_spend = Integer.valueOf(req.getParameter("min_spend").trim());
			if (min_spend == null) {
				errorMsgs.add("應達消費金額: 請勿空白");
			} else if (min_spend == 0) {
				errorMsgs.add("應達消費金額不可為0");
			}

			Integer event_coupon_discount = Integer.valueOf(req.getParameter("event_coupon_discount").trim());
			if (event_coupon_discount == null) {
				errorMsgs.add("折扣金額: 請勿空白");
			} else if (event_coupon_discount == 0) {
				errorMsgs.add("折扣金額不可為0");
			}

			java.sql.Timestamp start_date = null;
			try {
				start_date = java.sql.Timestamp.valueOf(req.getParameter("start_date").trim());
			} catch (IllegalArgumentException e) {
				start_date = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入起始使用時間!");
			}

			java.sql.Timestamp end_date = null;
			try {
				end_date = java.sql.Timestamp.valueOf(req.getParameter("end_date").trim());
			} catch (IllegalArgumentException e) {
				end_date = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入結束使用時間!");
			}

			Integer event_coupon_status = Integer.valueOf(req.getParameter("event_coupon_status").trim());
			if (event_coupon_status == null) {
				errorMsgs.add("活動優惠券狀態 : 請勿空白");
			}

			EventCouponVO eventCouponVO = new EventCouponVO();

			eventCouponVO.setHost_no(host_no);
			eventCouponVO.setEvent_coupon_name(event_coupon_name);
			eventCouponVO.setCoupon_code(coupon_code);
			eventCouponVO.setUsage_limit(usage_limit);
			eventCouponVO.setRemaining_times(remaining_times);
			eventCouponVO.setMin_spend(min_spend);
			eventCouponVO.setEvent_coupon_discount(event_coupon_discount);
			eventCouponVO.setStart_date(start_date);
			eventCouponVO.setEnd_date(end_date);
			eventCouponVO.setEvent_coupon_status(event_coupon_status);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("eventCouponVO", eventCouponVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/eventcoupon/addEventCoupon.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EventCouponService eventCouponSvc = new EventCouponService();
			eventCouponVO = eventCouponSvc.addEventCoupon(host_no, event_coupon_name, coupon_code, usage_limit,
					remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/eventcoupon/listAllEventCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEventCoupon.jsp
			successView.forward(req, res);
		}
	}
}
