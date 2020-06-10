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
            <td>${productLine.getProductLine()}</td>
            <td>${productLine.getDescription()}</td>
            <td>${productLine.getImage()}</td>
<%--            <td><a href="/products?command=edit}"><button>Edit</button></a></td>--%>
        </tr>
    <h3><a href="/products">Back to product</a></h3>
</table>
</body>
</html>
