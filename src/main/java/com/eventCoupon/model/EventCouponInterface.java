package com.eventCoupon.model;

import java.util.*;


public interface EventCouponInterface {
	public void insert(EventCouponVO event_CouponVO);

	public void update(EventCouponVO event_CouponVO);
	
//	public void delete(EventCouponVO event_CouponVO);

	public EventCouponVO findByPrimaryKey(Integer event_coupon_no);

	public List<EventCouponVO> getAll();
	
	  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EventCouponVO> getAll(Map<String, String[]> map); 
}