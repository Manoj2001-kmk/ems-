package ems;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

@WebServlet(urlPatterns = "/allemp")
public class FetchAllemployeedetails extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phno = req.getParameter("phno");
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(email);
		System.out.println(password);
		System.out.println(phno);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "root");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from emp");
			req.setAttribute("rs", rs);
			RequestDispatcher rd = req.getRequestDispatcher("allemp.jsp");
			rd.forward(req, resp);
			rs.close();
			s.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}
}