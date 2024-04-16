package com.host.model;

import java.sql.*;
import java.util.*;

import com.eventCoupon.model.EventCouponVO;

public class HostJDBCDAO implements HostDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ezban?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "fred890312";

	private static final String INSERT_STMT = "INSERT INTO host (insert into host (host_no, host_name) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT host_no, host_name FROM host";
	private static final String GET_ONE_STMT = "SELECT host_no, host_name FROM host where host_no = ?";
	private static final String GET_EventCoupons_ByHostno_STMT = "SELECT event_coupon_no, host_no,event_coupon_name, coupon_code, usage_limit, remaining_times, min_spend, event_coupon_discount, start_date, end_date, event_coupon_status FROM event_coupon where host_no = ? order by event_coupon_no";

	private static final String UPDATE = "UPDATE host set host_no=?, host_name=?";

	@Override
	public void insert(HostVO hostVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, hostVO.getHost_no());
			pstmt.setString(2, hostVO.getHost_name());

			pstmt.executeUpdate("set auto_increment_offset=10;");
			pstmt.executeUpdate("set auto_increment_increment=10;");
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
	public void update(HostVO hostVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, hostVO.getHost_no());
			pstmt.setString(2, hostVO.getHost_name());

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
	public HostVO findByPrimaryKey(Integer host_no) {
		HostVO hostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, host_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// host_no 也稱為 Domain objects
				hostVO = new HostVO();
				hostVO.setHost_no(rs.getInt("host_no"));
				hostVO.setHost_name(rs.getString("host_name"));
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
		return hostVO;
	}

	@Override
	public List<HostVO> getAll() {
		List<HostVO> list = new ArrayList<HostVO>();
		HostVO hostVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hostVO = new HostVO();
				hostVO.setHost_no(rs.getInt("host_no"));
				hostVO.setHost_name(rs.getString("host_name"));
				list.add(hostVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public Set<EventCouponVO> getEventCouponByHostno(Integer host_no) {
		Set<EventCouponVO> set = new LinkedHashSet<EventCouponVO>();
		EventCouponVO eventCouponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EventCoupons_ByHostno_STMT);
			pstmt.setInt(1, host_no);
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
				set.add(eventCouponVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

	public static void main(String[] args) {

		HostJDBCDAO dao = new HostJDBCDAO();

		// 新增
//		HostVO hostVO1 = new HostVO();
//		hostVO1.setHost_no(111);
//		hostVO1.setHost_name("未來攝影工作室");
//		dao.insert(hostVO1);

		// 修改
//		HostVO hostVO2 = new HostVO();
//		hostVO1.setHost_no(111);
//		hostVO1.setHost_name("未來攝影工作室");
//		dao.update(hostVO1);

		// 查詢
		HostVO hostVO3 = dao.findByPrimaryKey(10);
		System.out.print(hostVO3.getHost_no() + ",");
		System.out.print(hostVO3.getHost_name() + ",");
		System.out.println("---------------------");

		// 查詢廠商
		List<HostVO> list = dao.getAll();
		for (HostVO aDept : list) {
			System.out.print(aDept.getHost_no() + ",");
			System.out.print(aDept.getHost_name() + ",");
			System.out.println();
		}

		// 查詢某廠商的優惠券
		Set<EventCouponVO> set = dao.getEventCouponByHostno(10);
		for (EventCouponVO aEmp : set) {
			System.out.print(aEmp.getEvent_coupon_no() + ",");
			System.out.print(aEmp.getHost_no() + ",");
			System.out.print(aEmp.getEvent_coupon_name() + ",");
			System.out.print(aEmp.getCoupon_code() + ",");
			System.out.print(aEmp.getUsage_limit() + ",");
			System.out.print(aEmp.getRemaining_times() + ",");
			System.out.print(aEmp.getMin_spend() + ",");
			System.out.print(aEmp.getEvent_coupon_discount() + ",");
			System.out.print(aEmp.getStart_date() + ",");
			System.out.print(aEmp.getEnd_date() + ",");
			System.out.print(aEmp.getEvent_coupon_status() + ",");
			System.out.println();
		}
	}
}
