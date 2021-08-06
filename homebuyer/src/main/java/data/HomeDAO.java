package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import beans.Home;

public class HomeDAO implements HomeDAOInterface, AutoCloseable{
	
	private final static String url = "jdbc:mysql://localhost:3306/homebuyer";
	private final static String user = "root";
	private final static String password = "root";
	
	private Connection connection;

	public HomeDAO() throws Exception{
		super();
		connect();
	}
	
	@Override
	public void close() throws Exception{
		if(connection != null && !connection.isClosed()) {
			this.connection.close();
		}
	}
	
	public void connect() throws Exception{
		String url = "jdbc:mysql://localhost:3306/homebuyer";
		String user = "root";
		String password = "root";
		
		//load driver
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		this.connection = DriverManager.getConnection(url, user, password);
	}

	@Override
	public boolean save(Home home) throws SQLException {
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "INSERT INTO HOME(ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, home.getAddress());
			stmt.setInt(2, home.getSqft());
			stmt.setInt(3, home.getRooms());
			stmt.setInt(4, home.getBath());
			stmt.setInt(5, home.getPrice());
			int rows = stmt.executeUpdate();
			return rows > 0 ? true: false;
		}
		}

	@Override
	public List<Home> findAll() throws SQLException {
		List<Home> results = new LinkedList<>();
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "SELECT HOME_ID, ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE FROM HOME";
			System.out.println(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Home home = new Home(rs.getInt("HOME_ID"), rs.getString("ADDRESS"), rs.getInt("SQ_FT"), rs.getInt("ROOMS"), rs.getInt("BATHROOMS"), rs.getInt("PRICE"));
				results.add(home);
		}
		}catch (Exception e) {
			System.out.println("Find all failed");
			e.printStackTrace();
		}
		return results;
	}
	
	@Override
	public boolean update(Home home) throws SQLException{
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "UPDATE HOME SET ADDRESS = ?, SQ_FT = ?, ROOMS = ?, BATHROOMS = ?, PRICE = ? WHERE HOME_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, home.getAddress());
			stmt.setInt(2, home.getSqft());
			stmt.setInt(3, home.getRooms());
			stmt.setInt(4, home.getBath());
			stmt.setInt(5, home.getPrice());
			stmt.setInt(6, home.getId());
			int rows = stmt.executeUpdate();
			return rows > 0 ? true: false;
		}
	}
	
	@Override
	public boolean delete(int id) throws SQLException{
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "DELETE FROM HOME WHERE HOME_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			int rows = stmt.executeUpdate();
			return rows > 0 ? true: false;
		}
	}

}

interface HomeDAOInterface{
	//create
	public boolean save(Home home) throws SQLException;
	
	//get
	public List<Home> findAll() throws SQLException;
	
	//update
	public boolean update(Home home) throws SQLException;
	
	//delete
	public boolean delete(int id) throws SQLException;
}