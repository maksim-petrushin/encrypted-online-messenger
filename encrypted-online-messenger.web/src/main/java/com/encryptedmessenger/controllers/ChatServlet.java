package com.encryptedmessenger.controllers;

import java.io.IOException;

import com.encryptedmessenger.Configurations;
import com.encryptedmessenger.enteties.Message;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultUser;
import com.encryptedmessenger.services.MessageManagementService;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.MySqlMessageManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManagementService userManagementService = MySqlUserManagementService.getInstance();
	MessageManagementService messageManagementService = MySqlMessageManagementService.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message messages[];
		User currUser = (DefaultUser)request.getSession().getAttribute("loggedInUser");
		User friend;
		
		String baseUrl = request.getScheme()
				+ "://" 
				+ request.getServerName() 
				+ ":" + request.getServerPort()
				+ request.getServletContext().getContextPath();
		
		if(request.getParameter("phone") != null) {
			friend = userManagementService.getUserByPhone(request.getParameter("phone"));
		}
		else if(request.getSession().getAttribute("friend") != null) {
			friend = (DefaultUser)request.getSession().getAttribute("friend");
		}
		else {
			response.sendRedirect(baseUrl);
			return;
		}
		
		if(currUser != null) {
			messages = messageManagementService.getMessagesBetween(currUser, friend);
			request.getSession().setAttribute("messages", messages);
			request.getSession().setAttribute("friend", friend);
			
		}
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "homepage.jsp").forward(request, response);
	}

}
