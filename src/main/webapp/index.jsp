<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>JAX-RS Upload Form</h1>
    <form action="${pageContext.request.contextPath }/wscxf/upload/file" method="post" enctype="multipart/form-data">
       <p>
        Select a file : <input type="file" name="uploadedFile" size="50" />
       </p><%--
       <p>
       The Next file : <input type="file" name="uploadedFile2" size="50" />
       </p>
 
       --%><input type="submit" value="Upload It" />
    </form>
 
</body>
</html>