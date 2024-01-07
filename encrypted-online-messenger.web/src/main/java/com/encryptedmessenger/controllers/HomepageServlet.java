package com.encryptedmessenger.controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.encryptedmessenger.Configurations;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<User> friends = 
//		request.setAttribute("frineds", friends);
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "homepage.jsp").forward(request, response);
	}

}