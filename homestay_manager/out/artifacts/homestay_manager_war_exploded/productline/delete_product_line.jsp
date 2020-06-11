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
Bạn có chắc chắn muốn xóa dòng sẳn phẩm là <c:out value="${requestScope['productline']}"/> không ?
<form action="/productline?command=delete&productline=${requestScope['productline']}"  method="post">
    <button type="submit">Có</button>
    <button><a href="/productline">Không</a></button>
</form>
</body>
</html>
