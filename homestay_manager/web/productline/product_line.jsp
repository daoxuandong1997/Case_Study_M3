<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daoxu
  Date: 6/3/2020
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <style>
        table{
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>Products Lines</h1>
<table border="1px">
    <tr>
        <th>ProductLine</th>
        <th>Description</th>
        <th>Image</th>
    </tr>
<%--    <c:forEach items='${requestScope["product"]}' var="product">--%>
        <tr>
            <td><c:out value="${requestScope['productline'].getProductLine()}"/></td>
            <td><c:out value="${requestScope['productline'].getDescription()}"/></td>
            <td><img src="<c:out value="${requestScope['productline'].getImage()}"/>" width="75%" height="500px"></td>
<%--            <td><a href="/products?command=edit}"><button>Edit</button></a></td>--%>
        </tr>
    <h3><a href="/products">Back to product</a></h3>
</table>
</body>
</html>
