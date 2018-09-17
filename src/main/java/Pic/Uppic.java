package Pic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import JDBC.Perform;
import instance.Road;
import instance.User;


public class Uppic extends  HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("6666");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Object siss = session.getAttribute("user");
		System.out.println(siss);
		
		if(siss!=null) {
		
		// �ж��ϴ����Ƿ�Ϊmultipart/form-data����
		if (ServletFileUpload.isMultipartContent(request)) {
			 
		    try {
			// 1. ����DiskFileItemFactory�������û�������С����ʱ�ļ�Ŀ¼
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// System.out.println(System.getProperty("java.io.tmpdir"));//Ĭ����ʱ�ļ���
	 
			// 2. ����ServletFileUpload���󣬲������ϴ��ļ��Ĵ�С���ơ�
			ServletFileUpload sfu = new ServletFileUpload(factory);
			sfu.setSizeMax(10 * 1024 * 1024);// ��byteΪ��λ ���ܳ���10M 1024byte =
							 // 1kb 1024kb=1M 1024M = 1G
			sfu.setHeaderEncoding("utf-8");
			 
			// 3.
			// ����ServletFileUpload.parseRequest��������request���󣬵õ�һ�������������ϴ����ݵ�List����
			@SuppressWarnings("unchecked")
			List<FileItem> fileItemList = sfu.parseRequest(request);
			Iterator<FileItem> fileItems = fileItemList.iterator();
	 
			// 4. ����list��ÿ����һ��FileItem���󣬵�����isFormField�����ж��Ƿ����ϴ��ļ�
			while (fileItems.hasNext()) {
			    FileItem fileItem = fileItems.next();
			    // ��ͨ��Ԫ��
			    if (fileItem.isFormField()) {
				String name = fileItem.getFieldName();// name����ֵ
				String value = fileItem.getString("utf-8");// name��Ӧ��valueֵ
	 
				System.out.println(name + " = " + value);
			    }
			    // <input type="file">���ϴ��ļ���Ԫ��
			    else {
				String fileName = fileItem.getName();// �ļ�����
				System.out.println("ԭ�ļ�����" + fileName);// Koala.jpg
	 
				String suffix = fileName.substring(fileName.lastIndexOf('.'));
				System.out.println("��չ����" + suffix);// .jpg
	 
				// ���ļ�����Ψһ��
				String newFileName = new Date().getTime() + suffix;
				System.out.println("���ļ�����" + newFileName);// image\1478509873038.jpg
	 
				// 5. ����FileItem��write()������д���ļ�
				File file = new File("E:\\workspace\\Tp\\src\\picimg/" + newFileName);
				System.out.println(file.getAbsolutePath());
				fileItem.write(file);
	 
				// 6. ����FileItem��delete()������ɾ����ʱ�ļ�
				fileItem.delete();
				String username =(String)siss;
				String path ="/hh/" + newFileName; 
				Road root = new Road();
				root.setUsername(username);
				root.setRoad(path);
				
				if(root!=null) {
					Perform per = new Perform();
					per.saveRoad(root);
					response.sendRedirect("dontai/index.jsp");
					
				}
			    }
			    }
		    } catch (FileUploadException e) {
			e.printStackTrace();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}else {
			response.sendRedirect("index.jsp");
		}
		}
	}
	}
	    

	

