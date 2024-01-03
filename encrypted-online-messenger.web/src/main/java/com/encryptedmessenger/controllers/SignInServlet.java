package com.encryptedmessenger.controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.encryptedmessenger.services.impl.MySqlUserManagementService;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.enteties.User;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
	private UserManagementService userManagementService = MySqlUserManagementService.getInstance();

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
			response.sendRedirect(baseUrl + "/homepage.html");
		} else {
			response.sendRedirect(baseUrl + "/signin.html");
		}
	}

}
