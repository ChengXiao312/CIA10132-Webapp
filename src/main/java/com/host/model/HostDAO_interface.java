package com.host.model;

import java.util.List;
import java.util.Set;

import com.eventCoupon.model.EventCouponVO;


public interface HostDAO_interface {
	public void insert (HostVO hostVO);
	public void update (HostVO hostVO);
	 public HostVO findByPrimaryKey(Integer host_no);
     public List<HostVO> getAll();
   //查詢某廠商的優惠券(一對多)(回傳 Set)
     public Set<EventCouponVO> getEventCouponByHostno(Integer host_no);
}
