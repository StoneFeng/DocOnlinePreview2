package com.pdfPreview.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pdfPreview.util.DocConverter;

public class DocUploadConvertServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		String saveDirectory = request.getServletContext().getRealPath("/");
		int maxPostSize = 50 * 1024 * 1024 ;
		DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize,"UTF-8",dfp);
		Enumeration files = multi.getFileNames();
	     while (files.hasMoreElements()) {
	       String name = (String)files.nextElement();
	       File f = multi.getFile(name);
	       if(f!=null){
	         String fileName = multi.getFilesystemName(name);
	         //获取上传文件的扩展名
	         String extName=fileName.substring(fileName.lastIndexOf(".")+1);
	         //文件全路径
	         String lastFileName= saveDirectory + fileName;
	         //获取需要转换的文件名,将路径名中的'\'替换为'/'
	         String converfilename = saveDirectory.replaceAll("\\\\", "/")+"/"+fileName;
	         System.out.println("DocUploadConvertServlet.doPost 转换后的文件名: " + converfilename);
	         //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
	         DocConverter d = new DocConverter(converfilename);
	         //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
	     	 d.conver();
	     	 //调用getswfPath()方法，打印转换后的swf文件路径
	         System.out.println("SWF 文件生成的地址1 "+d.getswfPath());
	     	 //生成swf相对路径，以便传递给flexpaper播放器
	         String pdfPath = d.getPdfPath();
	         //将相对路径放入sessio中保存
	         String pdfFileName = pdfPath.substring(d.getPdfPath().lastIndexOf("/")+1, d.getPdfPath().length());
	         System.out.println("PDF 文件名 "+pdfFileName);
	         request.getSession().setAttribute("pdfFileName", pdfFileName);
	         request.setAttribute("lastFileName", lastFileName);
	         request.setAttribute("extName", extName);
	         request.getRequestDispatcher("/docUploadConvertAction.jsp").forward(request, resp);
	       }
	     }
	}

}
