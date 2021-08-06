package homebuyer;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data.HomeDAO;


public class DAOTest {

	private final static String url = "jdbc:mysql://localhost:3306/homebuyer";
	private final static String user = "root";
	private final static String password = "root";
	
	HomeDAO dao = new HomeDAO();
	
	@Before
	public void beforeTest() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String ddl = "CREATE TABLE `homebuyer`.`home` (`HOME_ID` INT NULL AUTO_INCREMENT,`ADDRESS` VARCHAR(100) NULL,`SQ_FT` INT NULL,`ROOMS` INT NULL,`BATHROOMS` INT NULL,`PRICE` INT NULL,PRIMARY KEY (`HOME_ID`))";
			stmt.executeUpdate(ddl);
			System.out.println("Test table created");
			conn.close();
		}catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void saveTest() {
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql = "INSERT INTO HOME(ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			dao.save(new Home());
			
			stmt.setString(1, "1111 dis way");
			stmt.setInt(2, 2200);
			stmt.setInt(3, 5);
			stmt.setInt(4, 2);
			stmt.setInt(5, 330000);
			stmt.executeUpdate();
			System.out.println("Home saved");
		}catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void updateTest() {
		
	}
	
	@Test
	public void findAllTest() {
		
	}
	
	@Test
	public void deleteTest() {
		
	}
	
	@After
	public void afterTest() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE HOMES");
			System.out.println("Test table dropped");
			conn.close();
		}catch (Exception e) {
			Assert.fail();
		}
	}
}
