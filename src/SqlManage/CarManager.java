package SqlManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Car;

public class CarManager {

	public List<String> selectimage(List<String> cid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<String> images = new ArrayList<String>();
		String image = null;
		try {
			con = ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			for (String s : cid) {
				String sql = "select cimage from t_carlist where cid=" + s;
				rs = st.executeQuery(sql);
				rs.next();
				image = rs.getString("cimage");
				if (!image.endsWith(".png")) {
					image = "nof.gif";
				}
				images.add(image);
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

		return images;
	}

	public void insert(Car car) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "insert into t_carlist(cname,ctype,price,cimage,insure) values (?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, car.getName());
			pst.setString(2, car.getType());
			pst.setString(3, car.getPrice());
			pst.setString(4, car.getImage());
			pst.setFloat(5, car.getInsure());
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

	public Car selectInfo(String id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Car car = new Car();
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from t_carlist where cid=" + id;
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			rs.next();
			car.setId(rs.getString("cid"));
			car.setName(rs.getString("cname"));
			car.setType(rs.getString("ctype"));
			car.setPrice(rs.getString("price"));
			car.setInsure(rs.getFloat("insure"));
			car.setImage(rs.getString("cimage"));

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

		return car;
	}

	public List<Car> selectAll() {
		List<Car> cars = new ArrayList<Car>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from t_carlist";
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Car car = new Car();
				car.setId(rs.getString("cid"));
				car.setName(rs.getString("cname"));
				car.setType(rs.getString("ctype"));
				car.setPrice(rs.getString("price"));
				car.setImage(rs.getString("cimage"));
				car.setInsure(rs.getFloat("insure"));
				if (!car.getImage().endsWith(".png")) {
					car.setImage("nof.gif");
				}
				car.setDesp(rs.getString("desp"));
				cars.add(car);
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

		return cars;
	}

	public void update(Car car, String id) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "update t_carlist set cname=?, ctype=?, price=?, cimage=? ,insure=? where cid = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, car.getName());
			pst.setString(2, car.getType());
			pst.setString(3, car.getPrice());
			pst.setString(4, car.getImage());
			pst.setFloat(5, car.getInsure());
			pst.setString(6, id);
			pst.executeUpdate();
			int res = pst.executeUpdate();

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

	public void update(String cid,String txt) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "update t_carlist set desp=? where cid = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, txt);
			pst.setString(2,cid);
			pst.executeUpdate();
			int res = pst.executeUpdate();

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
	
	public void delete(String id) {
		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "delete from t_carlist where cid=" + id;
			st.executeUpdate(sql);
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

	public List<String> selectid() {
		List<String> id = new ArrayList<String>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from t_carlist";
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				id.add(rs.getString("cid"));
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

		return id;
	}
}
