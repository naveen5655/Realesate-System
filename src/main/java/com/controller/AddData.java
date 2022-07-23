package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;
import com.model.UserRemote;

@WebServlet("/AddData")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(lookup = "java:global/Realestate/UserModel!com.model.UserRemote")
	UserRemote ER;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		try
		{
			User E = new User();
			int userid = Integer.parseInt(request.getParameter("t1"));
			String name = request.getParameter("t2");
			String email = request.getParameter("t3");
			String password = request.getParameter("t4");
			String phonenumber = request.getParameter("t5");
			E.setName(name);
			E.setEmail(email);
			E.setPassword(password);
			E.setPhonenumber(phonenumber);
			String ack =ER.insertData(E);
			pw.print(ack);
			
		}
		catch(Exception e)
		{
			pw.print(e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("add.jsp");
		rd.include(request,response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
