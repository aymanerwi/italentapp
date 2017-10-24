package com.exdev.italent.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_FOLDER = "c:/uploadedFiles/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("File upload....");
		System.out.println("Name: " + request.getParameter("name"));
		System.out.println("Filename: " + request.getParameter("filename"));

		String name = request.getParameter("name");
		String filename = request.getParameter("filename");
		
		if (name == null || filename == null) {
			response.getWriter().write("Invalid data");
//			response.sendRedirect("uploadfile.html");
			return;
		}

		if (name.length() == 0 || filename.length() == 0) {
			response.getWriter().write("Invalid data");
			return;
		}
		
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			response.getWriter().write("Can not create destination folder on server");
			return;
		}
		Part filePart = request.getPart("filename"); // Retrieves <input type="file" name="file">
		String fileName = name+".png";
		fileName = UPLOAD_FOLDER + filename;
		InputStream fileContent = filePart.getInputStream();

		System.out.println(fileContent.available());

		try {
			saveToFile(fileContent, fileName);
		} catch (IOException e) {
			response.getWriter().write("Can not save file");
		}
		response.getWriter().write(fileName);
	}

	private void saveToFile(InputStream inStream, String target) throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[inStream.available()];

		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

	private void createFolderIfNotExists(String dirName) throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}
}
