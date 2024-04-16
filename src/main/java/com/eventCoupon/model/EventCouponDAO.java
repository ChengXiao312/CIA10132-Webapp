package com.eventCoupon.model;

import java.sql.*;
import java.util.*;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EventCouponDAO implements EventCouponInterface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO event_coupon (host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT event_coupon_no, host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status FROM event_coupon order by event_coupon_no";
	private static final String GET_ONE_STMT = "SELECT event_coupon_no, host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status FROM event_coupon where event_coupon_no = ?";
	private static final String UPDATE = "UPDATE event_coupon set host_no=?, event_coupon_name=?, coupon_code=?, usage_limit=?, remaining_times=?, min_spend=?, event_coupon_discount=?, start_date=?, end_date=?, event_coupon_status=? where event_coupon_no = ?";


	@Override
	public void insert(EventCouponVO eventCouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, eventCouponVO.getHost_no());
			pstmt.setString(2, eventCouponVO.getEvent_coupon_name());
			pstmt.setString(3, eventCouponVO.getCoupon_code());
			pstmt.setInt(4, eventCouponVO.getUsage_limit());
			pstmt.setInt(5, eventCouponVO.getRemaining_times());
			pstmt.setInt(6, eventCouponVO.getMin_spend());
			pstmt.setInt(7, eventCouponVO.getEvent_coupon_discount());
			pstmt.setTimestamp(8, eventCouponVO.getStart_date());
			pstmt.setTimestamp(9, eventCouponVO.getEnd_date());
			pstmt.setInt(10, eventCouponVO.getEvent_coupon_status());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(EventCouponVO eventCouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt.setInt(1, eventCouponVO.getHost_no());
			pstmt.setString(2, eventCouponVO.getEvent_coupon_name());
			pstmt.setString(3, eventCouponVO.getCoupon_code());
			pstmt.setInt(4, eventCouponVO.getUsage_limit());
			pstmt.setInt(5, eventCouponVO.getRemaining_times());
			pstmt.setInt(6, eventCouponVO.getMin_spend());
			pstmt.setInt(7, eventCouponVO.getEvent_coupon_discount());
			pstmt.setTimestamp(8, eventCouponVO.getStart_date());
			pstmt.setTimestamp(9, eventCouponVO.getEnd_date());
			pstmt.setInt(10, eventCouponVO.getEvent_coupon_status());
			pstmt.setInt(11, eventCouponVO.getEvent_coupon_no()); // 最後設置 event_coupon_no 用於 WHERE 條件


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public EventCouponVO findByPrimaryKey(Integer event_coupon_no) {

		EventCouponVO eventCouponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, event_coupon_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				eventCouponVO = new EventCouponVO();
				eventCouponVO.setEvent_coupon_no(rs.getInt("event_coupon_no"));
				eventCouponVO.setHost_no(rs.getInt("host_no"));
				eventCouponVO.setEvent_coupon_name(rs.getString("event_coupon_name"));
				eventCouponVO.setCoupon_code(rs.getString("coupon_code"));
				eventCouponVO.setUsage_limit(rs.getInt("usage_limit"));
				eventCouponVO.setRemaining_times(rs.getInt("remaining_times"));
				eventCouponVO.setMin_spend(rs.getInt("min_spend"));
				eventCouponVO.setEvent_coupon_discount(rs.getInt("event_coupon_discount"));
				eventCouponVO.setStart_date(rs.getTimestamp("start_date"));
				eventCouponVO.setEnd_date(rs.getTimestamp("end_date"));
				eventCouponVO.setEvent_coupon_status(rs.getInt("event_coupon_status"));

			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return eventCouponVO;
	}

	@Override
	public List<EventCouponVO> getAll() {
		List<EventCouponVO> list = new ArrayList<EventCouponVO>();
		EventCouponVO eventCouponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				eventCouponVO = new EventCouponVO();
				eventCouponVO.setEvent_coupon_no(rs.getInt("event_coupon_no"));
				eventCouponVO.setHost_no(rs.getInt("host_no"));
				eventCouponVO.setEvent_coupon_name(rs.getString("event_coupon_name"));
				eventCouponVO.setCoupon_code(rs.getString("coupon_code"));
				eventCouponVO.setUsage_limit(rs.getInt("usage_limit"));
				eventCouponVO.setRemaining_times(rs.getInt("remaining_times"));
				eventCouponVO.setMin_spend(rs.getInt("min_spend"));
				eventCouponVO.setEvent_coupon_discount(rs.getInt("event_coupon_discount"));
				eventCouponVO.setStart_date(rs.getTimestamp("start_date"));
				eventCouponVO.setEnd_date(rs.getTimestamp("end_date"));
				eventCouponVO.setEvent_coupon_status(rs.getInt("event_coupon_status"));
				list.add(eventCouponVO);
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}
}
