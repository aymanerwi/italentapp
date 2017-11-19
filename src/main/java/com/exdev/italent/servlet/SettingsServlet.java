package com.exdev.italent.servlet;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import com.exdev.italent.api.ITalentApp;

/**
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SettingsServlet() {
		super();
	
		//ITalentApp.loadConfig();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setReqAtrr(request);
		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	private void setReqAtrr(HttpServletRequest request) {
		request.setAttribute("APP_NAME", ITalentApp.CONFIG.get("app_name"));
		request.setAttribute("AUTH_ANDROID", ITalentApp.CONFIG.get("AUTH_ANDROID"));
		request.setAttribute("AUTH_IOS", ITalentApp.CONFIG.get("AUTH_IOS"));
		request.setAttribute("APP_EMAIL_ID", ITalentApp.CONFIG.get("APP_EMAIL_ID"));
		request.setAttribute("EMAIL_PWD", ITalentApp.CONFIG.get("EMAIL_PWD"));
		request.setAttribute("MESSAGING_HTTP_API", ITalentApp.CONFIG.get("MESSAGING_HTTP_API"));
		request.setAttribute("MESSAGING_AUTH_KEY", ITalentApp.CONFIG.get("MESSAGING_AUTH_KEY"));
		request.setAttribute("GOOGLE_PROJECT_ID", ITalentApp.CONFIG.get("GOOGLE_PROJECT_ID"));
		request.setAttribute("SEND_SMS_API", ITalentApp.CONFIG.get("SEND_SMS_API"));
		request.setAttribute("SMS_USER_NAME", ITalentApp.CONFIG.get("SMS_USER_NAME"));
		request.setAttribute("SMS_TAG_NAME", ITalentApp.CONFIG.get("SMS_TAG_NAME"));
		request.setAttribute("SMTP", ITalentApp.CONFIG.get("SMTP"));
		request.setAttribute("DATE_FORMAT", ITalentApp.CONFIG.get("DATE_FORMAT"));
		request.setAttribute("SMS_USER_PWD", ITalentApp.CONFIG.get("SMS_USER_PWD"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ITalentApp.CONFIG = (Properties)
		// request.getServletContext().getAttribute("config");
		String va = request.getParameter("APP_NAME");
		ITalentApp.CONFIG.setProperty("app_name", request.getParameter("APP_NAME"));
		ITalentApp.CONFIG.setProperty("AUTH_ANDROID", request.getParameter("APP_NAME"));
		ITalentApp.CONFIG.setProperty("AUTH_IOS", request.getParameter("AUTH_IOS"));
		ITalentApp.CONFIG.setProperty("AUTH_IOS", request.getParameter("AUTH_ANDROID"));
		ITalentApp.CONFIG.setProperty("APP_EMAIL_ID", request.getParameter("APP_EMAIL_ID"));
		ITalentApp.CONFIG.setProperty("EMAIL_PWD", request.getParameter("EMAIL_PWD"));
		ITalentApp.CONFIG.setProperty("MESSAGING_HTTP_API", request.getParameter("MESSAGING_HTTP_API"));
		ITalentApp.CONFIG.setProperty("MESSAGING_AUTH_KEY", request.getParameter("MESSAGING_AUTH_KEY"));
		ITalentApp.CONFIG.setProperty("GOOGLE_PROJECT_ID", request.getParameter("GOOGLE_PROJECT_ID"));
		ITalentApp.CONFIG.setProperty("SEND_SMS_API", request.getParameter("SEND_SMS_API"));
		ITalentApp.CONFIG.setProperty("SMS_TAG_NAME", request.getParameter("SMS_TAG_NAME"));
		ITalentApp.CONFIG.setProperty("SMS_USER_NAME", request.getParameter("SMS_USER_NAME"));
		ITalentApp.CONFIG.setProperty("SMTP", request.getParameter("SMTP"));
		ITalentApp.CONFIG.setProperty("DATE_FORMAT", request.getParameter("DATE_FORMAT"));
		ITalentApp.CONFIG.setProperty("SMS_USER_PWD", request.getParameter("SMS_USER_PWD"));

//		String filePath = request.getServletContext().getRealPath(ITalentApp.CONFIG_FILE_PATH);
		
		String filePath = ITalentApp.CONFIG_FILE_PATH;
		ITalentApp.CONFIG.store(new FileWriter(filePath ),
				"Updated " + DateFormat.getDateTimeInstance().format(new Date()));
		response.sendRedirect("./settings");
	}

}
