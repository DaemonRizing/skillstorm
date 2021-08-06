package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Home;
import data.HomeDAO;

@WebServlet(name="home-servlet", urlPatterns="/homes")
public class HomeServlet extends HttpServlet{
	
	private CopyOnWriteArrayList<Home> homeList = new CopyOnWriteArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Home> homeList = null;
		try(HomeDAO dao = new HomeDAO()){
			homeList = dao.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(homeList);
		resp.getWriter().print(json);
		resp.setContentType("applicationjson");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Home home = mapper.readValue(req.getReader(), Home.class);
		try(HomeDAO dao = new HomeDAO()){
			dao.save(home);
		}catch(Exception e) {
			e.printStackTrace();
		}
		resp.setStatus(201);
		System.out.println("CREATED HOME");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Home home = mapper.readValue(req.getReader(), Home.class);
		try(HomeDAO dao = new HomeDAO()){
			dao.update(home);
		}catch(Exception e) {
			e.printStackTrace();
		}
		resp.setStatus(201);
		System.out.println("UPDATED HOME");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Home home = new ObjectMapper().readValue(req.getReader(), Home.class);
		try(HomeDAO dao = new HomeDAO()){
			dao.delete(home.getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		resp.setStatus(201);
		System.out.println("DELETED HOME");
		
	}
}