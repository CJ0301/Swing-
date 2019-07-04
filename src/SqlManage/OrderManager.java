package SqlManage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Entity.Car;
import Entity.Order;

public class OrderManager {

	public void insertOder(float sum,String uid, Car c,String time) {
		Connection con = null;
		PreparedStatement pst = null;
		Calendar red = Calendar.getInstance();
		red.add(Calendar.DAY_OF_MONTH, +Integer.valueOf(time)*31);
		try {
			con = ConnectionFactory.getConnection();
			String sql = "insert into t_order(uid,cid,total,rsd,red,otime) values (?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, uid);
			pst.setString(2, c.getId());
			pst.setFloat(3, sum);
			pst.setDate(4, new Date(System.currentTimeMillis()));
			pst.setDate(5,new java.sql.Date(red.getTime().getTime()));
			pst.setInt(6,Integer.parseInt(time));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public List<String> selectCids(String uid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String s = null;
		List<String> list = new ArrayList<String>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select cid from t_order where uid=" + uid;
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				s = rs.getString("cid");
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	public List<String> selectOids(String uid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String s = null;
		List<String> list = new ArrayList<String>();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select oid from t_order where uid=" + uid;
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				s = rs.getString("oid");
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	public void deleteEnd(List<Date> ends) {
		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = null;
			for(Date e:ends) {
				sql = "delete from t_order where red=\"" + e.toString()+"\"";
				st.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public Order orderInfo(String orders) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Order order = new Order();
		try {
			con = ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "select rsd,red,cid from t_order where oid=" + orders;
			rs = st.executeQuery(sql);
			rs.next();
			order.setStarttime(rs.getDate("rsd"));
			order.setEndtime(rs.getDate("red"));
			order.setCid(rs.getString("cid"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return order;
	}

	public List<Date> check(String uid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Date end = null;
		List<Date> list = new ArrayList<Date>();
		try {
			con = ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "select red from t_order where uid=" + uid;
			rs = st.executeQuery(sql);
			while(rs.next()) {
				end = rs.getDate("red");
				if((new Date(System.currentTimeMillis()).compareTo(end))>=0) {
					list.add(end);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return list;
	}
	
}
