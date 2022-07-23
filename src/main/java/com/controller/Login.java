package com.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.entity.User;
import com.model.UserRemote;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(lookup = "java:global/Realestate/UserModel!com.model.UserRemote")
	UserRemote ER;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
			EntityManager em = emf.createEntityManager();
			User E = new User();
			int userid = Integer.parseInt(request.getParameter("id"));
			
			String name = request.getParameter("t2");
			String email = request.getParameter("t3");
			String password = request.getParameter("t4");
			String phonenumber = request.getParameter("t5");
			E.setName(name);
			E.setEmail(email);
			E.setPassword(password);
			E.setPhonenumber(phonenumber);
			User ack =ER.findData(E);
			User ack1 = em.find(User.class, userid);
			pw.print(ack1);
			if(ack1.getUserid()==userid && ack1.getPassword().equals(password)) {
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
	        rd.forward(request, response);}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
		        rd.forward(request, response);
			}
			
		}
		catch(Exception e)
		{
			pw.print(e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
		rd.include(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
