package com.encryptedmessenger.controllers;

import java.io.IOException;
import java.util.ResourceBundle;

import com.encryptedmessenger.Configurations;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultUser;
import com.encryptedmessenger.services.FriendshipManagementService;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.MySqlFriendshipManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addfriend")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
	private UserManagementService userManagementService = MySqlUserManagementService.getInstance();
	private FriendshipManagementService friendshipManagementService = MySqlFriendshipManagementService.getInstance();
	
	private ResourceBundle rb = ResourceBundle.getBundle(Configurations.RESOURCE_BUNDLE_BASE_NAME);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "homepage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String baseUrl = request.getScheme()
			      + "://"
			      + request.getServerName()
			      + ":"
			      + request.getServerPort()
			      + request.getServletContext().getContextPath();
		User currUser = (DefaultUser)request.getSession().getAttribute(LOGGED_IN_USER_ATTR);
		User user = userManagementService.getUserByPhone(request.getParameter("users-phone"));
		if (user == null) {
			request.getSession().setAttribute("errMsg", rb.getString("addfriend.err.msg.user.doesntexists"));
			response.sendRedirect(baseUrl + "/homepage");
			return;
		}
		else  if(friendshipManagementService.doesFriendshipExist(user, currUser)){
			request.getSession().setAttribute("errMsg", rb.getString("addfriend.err.msg.user.alreadyfriend"));
			response.sendRedirect(baseUrl + "/homepage");
			return;
		}
		
		
		friendshipManagementService.createFriendship(currUser,user);
		response.sendRedirect(baseUrl + "/homepage");
	}

}
