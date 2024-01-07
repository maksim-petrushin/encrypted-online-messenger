package com.encryptedmessenger.controllers;

import java.io.IOException;

import com.encryptedmessenger.Configurations;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
	private UserManagementService userManagementService = MySqlUserManagementService.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "signin.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = userManagementService.getUserByPhone(request.getParameter("phone"));

		String baseUrl = request.getScheme()
				+ "://" 
				+ request.getServerName() 
				+ ":" + request.getServerPort()
				+ request.getServletContext().getContextPath();

		if (user != null && user.getPassword().equals(request.getParameter("password"))) {
			request.getSession().setAttribute(LOGGED_IN_USER_ATTR, user);
			response.sendRedirect(baseUrl + "/homepage");
		} else {	
			response.sendRedirect(baseUrl + "/signin");
		}
	}

}
