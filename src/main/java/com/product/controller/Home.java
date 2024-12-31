package com.product.controller;



import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.product.dao.ProductDAO;

/**
 * Servlet implementation class Home
 */
@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductDAO userDAO;
	
	public void init() {
		userDAO = new ProductDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		RequestDispatcher dispatcher;
		
		switch (action) {
		case "/register":
			 dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
			break;
		case "/login":
			 dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

	
}