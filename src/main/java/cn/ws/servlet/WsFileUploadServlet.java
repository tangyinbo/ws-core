package cn.ws.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class WsFileUploadServlet extends HttpServlet {
	private String tempFilePath;
	private String imgPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		tempFilePath = config.getServletContext().getRealPath("/") + File.separator + "temp";
		File f = new File(tempFilePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		imgPath = config.getServletContext().getRealPath("/") + File.separator + "img";
		f = new File(imgPath);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			dealUpload2(req, resp);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			dealUpload2(req, resp);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dealUpload2(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException{
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(100);
		factory.setRepository(new File(tempFilePath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(1024 * 1024 * 1024);
		List<FileItem> items = upload.parseRequest(req);
		InputStream ins = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis  = null;
		for (FileItem item : items) {
			if (item.isFormField()) {
				continue;
			}
			String s = item.getName();
			long size = item.getSize();
			try {
				ins = item.getInputStream();
				os = new FileOutputStream(new File(imgPath,s));
				bos = new BufferedOutputStream(os);
				bis = new BufferedInputStream(ins);
				byte[] bt = new byte[1024];
				int len = 0;
				while((len=bis.read(bt))!=-1){
					bos.write(bt, 0, len);
				}
				bos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					ins.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}

	}

	private void dealRequest(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File f = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(f);
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		List<FileItem> items = servletFileUpload.parseRequest(req);
		for (FileItem item : items) {
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString();
				System.out.println(name + "   :\t" + value);
				continue;
			}
			String fileName = item.getFieldName();
			String contentType = item.getContentType();
			String name = item.getName();
			System.out.println("fileName:  ->" + fileName);
			System.out.println("contentType:  ->" + contentType);
			System.out.println("name:  ->" + name);
		}
	}
}
