package com.encryptedmessenger.controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.encryptedmessenger.Configurations;
import com.encryptedmessenger.enteties.Message;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultUser;
import com.encryptedmessenger.services.MessageManagementService;
import com.encryptedmessenger.services.UserManagementService;
import com.encryptedmessenger.services.impl.MySqlMessageManagementService;
import com.encryptedmessenger.services.impl.MySqlUserManagementService;

/**
 * Servlet implementation class DraftServlet
 */
@WebServlet("/draft")
public class DraftServlet extends HttpServlet {
	UserManagementService userManagementService = MySqlUserManagementService.getInstance();
	MessageManagementService messageManagementService = MySqlMessageManagementService.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messagesBody = request.getParameter("draft");
		Message messages[];
		
		String baseUrl = request.getScheme()
				+ "://" 
				+ request.getServerName() 
				+ ":" + request.getServerPort()
				+ request.getServletContext().getContextPath();
		
		User currUser = (DefaultUser)request.getSession().getAttribute("loggedInUser");
		User friend = userManagementService.getUserByPhone(request.getParameter("receiver"));
		if(currUser != null) {
			messageManagementService.sendMessage(currUser, friend, messagesBody);
			messages = messageManagementService.getMessagesBetween(currUser, friend);
			request.setAttribute("messages", messages);
			request.setAttribute("friend", friend);
			
		}
		response.sendRedirect(baseUrl + "/chat");
	}

}
