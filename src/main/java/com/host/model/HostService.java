package com.host.model;

import java.util.*;
import com.eventCoupon.model.EventCouponVO;



public class HostService {
	private HostDAO_interface dao;
	
	public HostService() {
		dao = new HostJDBCDAO();
	}
	public List<HostVO> getAll() {
		return dao.getAll();
	}

	public HostVO getOneHost(Integer host_no) {
		return dao.findByPrimaryKey(host_no);
	}

	public Set<EventCouponVO> getEventCouponsByHostno(Integer host_no) {
		return dao.getEventCouponByHostno(host_no);
	}
}
