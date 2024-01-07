package com.encryptedmessenger.controllers;

import java.io.IOException;
import java.util.ResourceBundle;

import com.encryptedmessenger.Configurations;
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
	private ResourceBundle rb = ResourceBundle.getBundle(Configurations.RESOURCE_BUNDLE_BASE_NAME);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "signup.jsp").forward(request, response);
	}
	
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
		
		User userByPhone = userManagementService.getUserByPhone(user.getPhone());
		
		if (userByPhone != null) {
			request.getSession().setAttribute("errMsg", rb.getString("signup.err.msg.phone.exists"));
			response.sendRedirect(baseUrl + "/signup");
			return;
		}

		if (!user.getPassword().equals(request.getParameter("repeatPassword"))) {
			request.getSession().setAttribute("errMsg", rb.getString("signup.err.msg.repeat.password"));
			response.sendRedirect(baseUrl + "/signup");
			return;
		}
		
		userManagementService.registerUser(user);
		response.sendRedirect(baseUrl + "/signin");
	}

}


