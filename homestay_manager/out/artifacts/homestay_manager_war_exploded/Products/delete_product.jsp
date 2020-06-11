<%--
  Created by IntelliJ IDEA.
  User: daoxu
  Date: 6/11/2020
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Bạn có chắc chắn muốn xóa sẳn phẩm có id là <c:out value="${requestScope['id']}"/> không ?
<form action="/products?command=delete&id=${requestScope['id']}"  method="post">
    <button type="submit">Có</button>
    <button><a href="/products">Không</a></button>
</form>
</body>
</html>
