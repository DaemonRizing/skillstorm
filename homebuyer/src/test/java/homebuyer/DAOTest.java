package homebuyer;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import beans.Home;
import data.HomeDAO;


public class DAOTest {

	private final static String url = "jdbc:mysql://localhost:3306/homebuyer";
	private final static String user = "root";
	private final static String password = "root";
	
	
	
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
	public void saveTest(){
		try {
			HomeDAO dao = new HomeDAO();
			dao.save(new Home(0, "1111 dis way", 2200, 5, 2, 330000));
			System.out.println("Home saved");
		}catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void updateTest() {
		try {
			HomeDAO dao = new HomeDAO();
			dao.update(new Home(1, "2365 dat way", 1550, 2, 1, 120000));
			System.out.println("Home updated");
		}catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void findAllTest() {
		try {
			HomeDAO dao = new HomeDAO();
			List<Home> hm = new LinkedList<>();
			hm = dao.findAll();
			System.out.println(hm);
		}catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void deleteTest() {
		try {
			HomeDAO dao = new HomeDAO();
			dao.delete(1);
			System.out.println("Home deleted");
		}catch (Exception e){
			fail();
		}
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
