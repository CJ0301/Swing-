package SqlManage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Entity.Userinfo;

public class UserManager {

	public int update(Float money, String uid) {
		Connection con = null;
		PreparedStatement pst = null;
		int res = 0;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "update t_userinfo set amount=amount-? where uid = ?";
			pst = con.prepareStatement(sql);
			pst.setFloat(1, money);
			pst.setString(2, uid);
			res = pst.executeUpdate();
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
		return res;
	}

	public Userinfo findUser(String uid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Userinfo user = new Userinfo();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from t_userinfo where uid=" + uid;
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			rs.next();
			user.setTname(rs.getString("tname"));
			user.setAmount(rs.getFloat("amount"));

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

		return user;
	}

	public List<Userinfo> findByTname(String tname) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Userinfo user = null;
		List<Userinfo> list = new ArrayList<Userinfo>();
		try {
			con = ConnectionFactory.getConnection();

			String sql = "select password,uid from t_userinfo where tname =?";
			pst = con.prepareStatement(sql);
			pst.setString(1, tname);
			rs = pst.executeQuery();
			if (rs.next()) {
				Userinfo u = new Userinfo();
				u.setId(rs.getString("uid"));
				u.setPassword(rs.getString("password"));
				list.add(u);
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
		return list;
	}

	public int save(Userinfo ui) {

		String sql = "INSERT INTO t_userinfo" + "(phone,password,tname,id,genda,regtime) " + "VALUES(?,?,?,?,?,?)";

		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ui.getPhone());
			ps.setString(2, ui.getPassword());
			ps.setString(3, ui.getTname());
			ps.setString(4, ui.getId());
			ps.setInt(5, ui.getGenda());
			ps.setDate(6, new Date(System.currentTimeMillis()));

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Float recharge(String uid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String s = null;
		Float amount = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select amount from t_userinfo where uid=" + uid;
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			rs.next();
			amount = rs.getFloat("amount");
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
		return amount;
	}

	public int recharge(float money, String uid) {
		Connection con = null;
		PreparedStatement pst = null;
		int res = 0;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "update t_userinfo set amount=amount+? where uid = ?";
			pst = con.prepareStatement(sql);
			pst.setFloat(1, money);
			pst.setString(2, uid);
			res = pst.executeUpdate();
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
		return res;

	}

}
