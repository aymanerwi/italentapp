package com.exdev.italent.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

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
@WebServlet(name = "FileUploadServlet", urlPatterns = { "/upload" })
@MultipartConfig(maxFileSize=7340032)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class.getCanonicalName());

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

		response.setContentType("text/html;charset=UTF-8");

		final String name = request.getParameter("name");
		final String path = request.getServletContext().getRealPath("/images");
		final Part filePart = request.getPart("file");
		 final String fileExt = getFileExt(filePart);
		final String fileName = name +fileExt;
		final String url = getServerUri(request) + "/images/";

		OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter writer = response.getWriter();

		try {
			out = new FileOutputStream(new File(path + File.separator + fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			String fileUrl = url + fileName;
			writer.println(fileUrl);
			LOGGER.log(Level.INFO, "File {0} being uploaded to {1}", new Object[] { fileName, path });
			LOGGER.log(Level.INFO, "File {0} url is {1}", new Object[] { fileName, url + fileName });
		} catch (FileNotFoundException fne) {
			writer.println("You either did not specify a file to upload or are "
					+ "trying to upload a file to a protected or nonexistent " + "location.");
			writer.println("<br/> ERROR: " + fne.getMessage());

			LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[] { fne.getMessage() });
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
	}

	private String getFileExt(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				String fileName;
				fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf("."));
			}
		}
		return null;
	}

	private String getServerUri(HttpServletRequest request) {
		String uri = request.getScheme() + "://" + request.getServerName()
				+ ("http".equals(request.getScheme()) && request.getServerPort() == 80
						|| "https".equals(request.getScheme()) && request.getServerPort() == 443 ? ""
								: ":" + request.getServerPort())
				+ request.getContextPath();

		return uri;
	}
}