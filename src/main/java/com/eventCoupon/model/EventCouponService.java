package com.eventCoupon.model;

import java.util.List;

public class EventCouponService {
	private EventCouponInterface dao;

	public EventCouponService() {
		dao = new EventCouponJDBCDAO();
	}

	public EventCouponVO addEventCoupon(Integer host_no, String event_coupon_name, String coupon_code,
			Integer usage_limit, Integer remaining_times, Integer min_spend, Integer event_coupon_discount,
			java.sql.Timestamp start_date, java.sql.Timestamp end_date, Integer event_coupon_status) {

		EventCouponVO eventCouponVO = new EventCouponVO();

//		eventCouponVO.setEvent_coupon_no(event_coupon_no);
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
		dao.insert(eventCouponVO);
		
		return eventCouponVO;

	}

	public EventCouponVO updateEventCoupon(Integer event_coupon_no, Integer host_no, String event_coupon_name,
			String coupon_code, Integer usage_limit, Integer remaining_times, Integer min_spend,
			Integer event_coupon_discount, java.sql.Timestamp start_date, java.sql.Timestamp end_date,
			Integer event_coupon_status) {

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
		dao.update(eventCouponVO);
		
		return eventCouponVO;
	}

	public EventCouponVO getOneEventCoupon(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EventCouponVO> getAll() {
		return dao.getAll();
	}
}
