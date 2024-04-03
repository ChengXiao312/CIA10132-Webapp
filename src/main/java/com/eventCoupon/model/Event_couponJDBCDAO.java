package com.eventCoupon.model;

import java.sql.*;
import java.util.*;



public class Event_couponJDBCDAO implements Event_coupon_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "fred890312";

	private static final String INSERT_STMT = "INSERT INTO event_coupon (host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT event_coupon_no, host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status FROM event_coupon order by event_coupon_no";
	private static final String GET_ONE_STMT = "SELECT event_coupon_no, host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status FROM event_coupon where event_coupon_no = ?";
	private static final String UPDATE = "UPDATE event_coupon set host_no=?, event_coupon_name=?, coupon_code=?, usage_limit=?, remaining_times=?, min_spend=?, event_coupon_discount=?, start_date=?, end_date=?, event_coupon_status=? where event_coupon_no = ?";

	@Override
	public void insert(Event_couponVO event_CouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, event_CouponVO.getHost_no());
			pstmt.setString(2, event_CouponVO.getEvent_coupon_name());
			pstmt.setString(3, event_CouponVO.getCoupon_code());
			pstmt.setInt(4, event_CouponVO.getUsage_limit());
			pstmt.setInt(5, event_CouponVO.getRemaining_times());
			pstmt.setInt(6, event_CouponVO.getMin_spend());
			pstmt.setInt(7, event_CouponVO.getEvent_coupon_discount());
			pstmt.setTimestamp(8, event_CouponVO.getStart_date());
			pstmt.setTimestamp(9, event_CouponVO.getEnd_date());
			pstmt.setInt(10, event_CouponVO.getEvent_coupon_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(Event_couponVO event_CouponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, event_CouponVO.getEvent_coupon_no());
			pstmt.setInt(2, event_CouponVO.getHost_no());
			pstmt.setString(3, event_CouponVO.getEvent_coupon_name());
			pstmt.setString(4, event_CouponVO.getCoupon_code());
			pstmt.setInt(5, event_CouponVO.getUsage_limit());
			pstmt.setInt(6, event_CouponVO.getRemaining_times());
			pstmt.setInt(7, event_CouponVO.getMin_spend());
			pstmt.setInt(8, event_CouponVO.getEvent_coupon_discount());
			pstmt.setTimestamp(9, event_CouponVO.getStart_date());
			pstmt.setTimestamp(10, event_CouponVO.getEnd_date());
			pstmt.setInt(11, event_CouponVO.getEvent_coupon_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public Event_couponVO findByPrimaryKey(Integer event_coupon_no) {

		Event_couponVO event_couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, event_coupon_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				event_couponVO = new Event_couponVO();
				event_couponVO.setEvent_coupon_no(rs.getInt("event_coupon_no"));
				event_couponVO.setHost_no(rs.getInt("host_no"));
				event_couponVO.setEvent_coupon_name(rs.getString("event_coupon_name"));
				event_couponVO.setCoupon_code(rs.getString("coupon_code"));
				event_couponVO.setUsage_limit(rs.getInt("usage_limit"));
				event_couponVO.setRemaining_times(rs.getInt("remaining_times"));
				event_couponVO.setMin_spend(rs.getInt("min_spend"));
				event_couponVO.setEvent_coupon_discount(rs.getInt("event_coupon_discount"));
				event_couponVO.setStart_date(rs.getTimestamp("start_date"));
				event_couponVO.setEnd_date(rs.getTimestamp("end_date"));
				event_couponVO.setEvent_coupon_status(rs.getInt("event_coupon_status"));

			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

		return event_couponVO;
	}

	@Override
	public List<Event_couponVO> getAll() {

		List<Event_couponVO> list = new ArrayList<Event_couponVO>();
		Event_couponVO event_couponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				event_couponVO = new Event_couponVO();
				event_couponVO.setEvent_coupon_no(rs.getInt("event_coupon_no"));
				event_couponVO.setHost_no(rs.getInt("host_no"));
				event_couponVO.setEvent_coupon_name(rs.getString("event_coupon_name"));
				event_couponVO.setCoupon_code(rs.getString("coupon_code"));
				event_couponVO.setUsage_limit(rs.getInt("usage_limit"));
				event_couponVO.setRemaining_times(rs.getInt("remaining_times"));
				event_couponVO.setMin_spend(rs.getInt("min_spend"));
				event_couponVO.setEvent_coupon_discount(rs.getInt("event_coupon_discount"));
				event_couponVO.setStart_date(rs.getTimestamp("start_date"));
				event_couponVO.setEnd_date(rs.getTimestamp("end_date"));
				event_couponVO.setEvent_coupon_status(rs.getInt("event_coupon_status"));

			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
