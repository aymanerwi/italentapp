package com.exdev.italent.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = { "/upload" })
@MultipartConfig(maxFileSize = 7340032)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class.getCanonicalName());

	private static String UPLOAD_DIR = "";
	private static final String DOWNLOAD_DIR = "uploads";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UPLOAD_DIR = getServletContext().getInitParameter("UPLOAD_DIR");
		response.setContentType("text/html;charset=UTF-8");
		if (UPLOAD_DIR.isEmpty())
			throw new ServletException("Error: upload dir not configured");
		File fileSaveDir = new File(UPLOAD_DIR);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		final String name = request.getParameter("name");
		final Part filePart = request.getPart("file");

		OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter writer = response.getWriter();
		if (name == null || name.length() == 0) {
			throw new ServletException("Error: the name must not be empty");
		}

		if (filePart == null || filePart.getSize() == 0) {

			throw new ServletException("Error: the file must not be empty or size 0");

		}
		final String fileExt = getFileExt(filePart);
		final String fileName = name + fileExt;
		final String url = getServerUri(request) + "/" + DOWNLOAD_DIR;
		try {
			File file = new File(UPLOAD_DIR + "/" + fileName);
			out = new FileOutputStream(file);
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			String fileUrl = url + "/" + URLEncoder.encode(fileName, "UTF-8");
			writer.println(fileUrl);

			LOGGER.log(Level.INFO, "File {0} being uploaded to {1}", new Object[] { fileName, file.getAbsolutePath() });
			LOGGER.log(Level.INFO, "File {0} url is {1}", new Object[] { fileName, fileUrl });
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
								: ":" + request.getServerPort());

		return uri;
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UPLOAD_DIR = getServletContext().getInitParameter("UPLOAD_DIR");
		String fileName = req.getParameter("name");
		File f = new File(UPLOAD_DIR + "/" + fileName);
		if (f.exists()) {
			f.delete();
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println(fileName + " Deleted");
		}
		else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			resp.getWriter().println(fileName + " not exists");
		}
	}

}
