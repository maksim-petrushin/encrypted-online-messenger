package com.encryptedmessenger.controllers;

import java.io.IOException;

import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultUser;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManagementService userManagementService = MySqlUserManagementService.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String baseUrl = request.getScheme()
			      + "://"
			      + request.getServerName()
			      + ":"
			      + request.getServerPort()
			      + request.getServletContext().getContextPath();

		User user = new DefaultUser();
		
		user.setNickName(request.getParameter("nickName"));
		user.setPhone(request.getParameter("phone"));
		user.setPassword(request.getParameter("password"));
		
		userManagementService.registerUser(user);
		response.sendRedirect(baseUrl + "/signin.html");
	}

}


