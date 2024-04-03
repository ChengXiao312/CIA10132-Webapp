package com.eventCoupon.model;

import java.sql.Timestamp;

public class Event_couponVO {
	private Integer event_coupon_no;
	private Integer host_no;
	private String event_coupon_name;
	private String coupon_code;
	private Integer usage_limit;
	private Integer remaining_times;
	private Integer min_spend;
	private Integer event_coupon_discount;
	private Timestamp start_date;
	private Timestamp end_date;
	private Integer event_coupon_status;
	public Integer getEvent_coupon_no() {
		return event_coupon_no;
	}
	public void setEvent_coupon_no(Integer event_coupon_no) {
		this.event_coupon_no = event_coupon_no;
	}
	public Integer getHost_no() {
		return host_no;
	}
	public void setHost_no(Integer host_no) {
		this.host_no = host_no;
	}
	public String getEvent_coupon_name() {
		return event_coupon_name;
	}
	public void setEvent_coupon_name(String event_coupon_name) {
		this.event_coupon_name = event_coupon_name;
	}
	public String getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}
	public Integer getUsage_limit() {
		return usage_limit;
	}
	public void setUsage_limit(Integer usage_limit) {
		this.usage_limit = usage_limit;
	}
	public Integer getRemaining_times() {
		return remaining_times;
	}
	public void setRemaining_times(Integer remaining_times) {
		this.remaining_times = remaining_times;
	}
	public Integer getMin_spend() {
		return min_spend;
	}
	public void setMin_spend(Integer min_spend) {
		this.min_spend = min_spend;
	}
	public Integer getEvent_coupon_discount() {
		return event_coupon_discount;
	}
	public void setEvent_coupon_discount(Integer event_coupon_discount) {
		this.event_coupon_discount = event_coupon_discount;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	public Integer getEvent_coupon_status() {
		return event_coupon_status;
	}
	public void setEvent_coupon_status(Integer event_coupon_status) {
		this.event_coupon_status = event_coupon_status;
	}
	
	
	
	
}
