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
<h1>Products</h1>
<p>
    <a href="/products?command=create">Create a new product</a>
</p>
<table border="1px">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Vendor</th>
        <th>ProductLine</th>
        <th>Quantity</th>
        <th>Update</th>
    </tr>
    <c:forEach items='${requestScope["products"]}' var="product">
        <tr>
            <td>${product.getProductCode()}</td>
            <td>${product.getProductName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getProductVendor()}</td>
            <td>${product.getProductLine()}</td>
            <td>${product.getQuantity()}</td>
            <td><a href="/products?command=edit&id=${product.getProductCode()}"><button>Edit</button></a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
