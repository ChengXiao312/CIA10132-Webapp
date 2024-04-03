package com.eventCoupon.model;

import java.util.*;

public interface Event_coupon_interface {
	public void insert(Event_couponVO event_CouponVO);

	public void update(Event_couponVO event_CouponVO);
	
//	public void delete(Event_couponVO event_CouponVO);

	public Event_couponVO findByPrimaryKey(Integer event_coupon_no);

	public List<Event_couponVO> getAll();
}
