package com.eventCoupon.model;

import java.sql.*;
import java.util.*;

public class EventCouponJDBCDAO implements EventCouponInterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ezban?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "fred890312";

	private static final String INSERT_STMT = "INSERT INTO event_coupon (host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT event_coupon_no, host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status FROM event_coupon order by event_coupon_no";
	private static final String GET_ONE_STMT = "SELECT event_coupon_no, host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status FROM event_coupon where event_coupon_no = ?";
	private static final String UPDATE = "UPDATE event_coupon set host_no=?, event_coupon_name=?, coupon_code=?, usage_limit=?, remaining_times=?, min_spend=?, event_coupon_discount=?, start_date=?, end_date=?, event_coupon_status=? where event_coupon_no = ?";

	@Override
	public void insert(EventCouponVO eventCouponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(EventCouponVO eventCouponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, eventCouponVO.getEvent_coupon_no());
			pstmt.setInt(2, eventCouponVO.getHost_no());
			pstmt.setString(3, eventCouponVO.getEvent_coupon_name());
			pstmt.setString(4, eventCouponVO.getCoupon_code());
			pstmt.setInt(5, eventCouponVO.getUsage_limit());
			pstmt.setInt(6, eventCouponVO.getRemaining_times());
			pstmt.setInt(7, eventCouponVO.getMin_spend());
			pstmt.setInt(8, eventCouponVO.getEvent_coupon_discount());
			pstmt.setTimestamp(9, eventCouponVO.getStart_date());
			pstmt.setTimestamp(10, eventCouponVO.getEnd_date());
			pstmt.setInt(11, eventCouponVO.getEvent_coupon_status());

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
	public EventCouponVO findByPrimaryKey(Integer event_coupon_no) {

		EventCouponVO eventCouponVO = null;
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) {
		EventCouponJDBCDAO dao = new EventCouponJDBCDAO();

//		新增
//		EventCouponVO eventCouponVO1 = new EventCouponVO();
//		eventCouponVO1.setHost_no(11);
//		eventCouponVO1.setEvent_coupon_name("EventA1");
//		eventCouponVO1.setCoupon_code("CODEA1");
//		eventCouponVO1.setUsage_limit(100);
//		eventCouponVO1.setRemaining_times(50);
//		eventCouponVO1.setMin_spend(1000);
//		eventCouponVO1.setEvent_coupon_discount(100);
//		eventCouponVO1.setStart_date(java.sql.Timestamp.valueOf("2024-05-01"));
//		eventCouponVO1.setEnd_date(java.sql.Timestamp.valueOf("2024-05-11"));
//		eventCouponVO1.setEvent_coupon_status(1);
//		dao.insert(eventCouponVO1);

//		修改
//		EventCouponVO eventCouponVO2 = new EventCouponVO();
//		eventCouponVO2.setHost_no(11);
//		eventCouponVO2.setEvent_coupon_name("EventA1");
//		eventCouponVO2.setCoupon_code("CODEA1");
//		eventCouponVO2.setUsage_limit(100);
//		eventCouponVO2.setRemaining_times(50);
//		eventCouponVO2.setMin_spend(1000);
//		eventCouponVO2.setEvent_coupon_discount(100);
//		eventCouponVO2.setStart_date(java.sql.Timestamp.valueOf("2024-05-01"));
//		eventCouponVO2.setEnd_date(java.sql.Timestamp.valueOf("2024-05-11"));
//		eventCouponVO2.setEvent_coupon_status(1);
//		dao.update(eventCouponVO2);

//		查詢單一
//		EventCouponVO eventCouponVO3 = dao.findByPrimaryKey(5001);
//
//		System.out.print(eventCouponVO3.getHost_no() + ",");
//		System.out.print(eventCouponVO3.getEvent_coupon_name() + ",");
//		System.out.print(eventCouponVO3.getCoupon_code() + ",");
//		System.out.print(eventCouponVO3.getUsage_limit() + ",");
//		System.out.print(eventCouponVO3.getRemaining_times() + ",");
//		System.out.print(eventCouponVO3.getMin_spend() + ",");
//		System.out.print(eventCouponVO3.getEvent_coupon_discount() + ",");
//		System.out.print(eventCouponVO3.getStart_date() + ",");
//		System.out.print(eventCouponVO3.getEnd_date() + ",");
//		System.out.print(eventCouponVO3.getEvent_coupon_status());
//		System.out.println("---------------------");

//		查詢全部
		List<EventCouponVO> list = dao.getAll();
		for (EventCouponVO aEvent_coupon : list) {
			System.out.print(aEvent_coupon.getHost_no() + ",");
			System.out.print(aEvent_coupon.getEvent_coupon_name() + ",");
			System.out.print(aEvent_coupon.getCoupon_code() + ",");
			System.out.print(aEvent_coupon.getUsage_limit() + ",");
			System.out.print(aEvent_coupon.getRemaining_times() + ",");
			System.out.print(aEvent_coupon.getMin_spend() + ",");
			System.out.print(aEvent_coupon.getEvent_coupon_discount() + ",");
			System.out.print(aEvent_coupon.getStart_date() + ",");
			System.out.print(aEvent_coupon.getEnd_date() + ",");
			System.out.print(aEvent_coupon.getEvent_coupon_status());
			System.out.println();
		}
	}
}
