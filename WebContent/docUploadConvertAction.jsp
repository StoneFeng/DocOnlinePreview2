<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body {margin-top:100px;background:#fff;font-family: Verdana, Tahoma;}
    a {color:#CE4614;}
    #msg-box {color: #CE4614; font-size:0.9em;text-align:center;}
    #msg-box .logo {border-bottom:5px solid #ECE5D9;margin-bottom:20px;padding-bottom:10px;}
    #msg-box .title {font-size:1.4em;font-weight:bold;margin:0 0 30px 0;}
    #msg-box .nav {margin-top:20px;}
</style>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>上传的文件</td>
				<td><%=request.getAttribute("lastFileName") %></td>
			</tr>
			<tr>
				<td>文件类型</td>
				<td><%=request.getAttribute("extName") %></td>
			</tr>
		</table>
		<hr>
	</div>
	<div>
		<form name="viewForm" id="form_swf" action="pdfjs-1.5.188-dist/web/viewer.html?file=../../<%=session.getAttribute("pdfFileName")%>" method="POST">
			<input type='submit' value='预览' class='BUTTON SUBMIT'/>
		</form>
	</div>
</body>
</html>