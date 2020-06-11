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
            align-items: center;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" integrity="sha256-h20CPZ0QyXlBuAw7A+KluUYx/3pK+c7lYEpqLTlxjYQ=" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" integrity="sha256-KzZiKy0DWYsnwMF+X1DvQngQ2/FxF7MF3Ff72XcpuPs=" crossorigin="anonymous"></script>

</head>
<body>
<h1>Products</h1>
<p>
    <a href="/products?command=create">Tạo 1 sản phẩm mới</a>
</p>
<table border="1px">
    <tbody>
    <tr>
        <th>Mã Phòng</th>
        <th>Tên Phòng</th>
        <th>Giá Phòng</th>
        <th>Địa Điểm</th>
        <th>Loại Phòng</th>
        <th>Số Phòng còn trống </th>
        <th>Cập nhật</th>
        <th>Xóa</th>
    </tr>
    <c:forEach items='${requestScope["products"]}' var="product">
        <tr>
            <td>${product.getProductCode()}</td>
            <td>${product.getProductName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getProductVendor()}</td>
<%--            <td><a href="/products?command=lines">${product.getProductLine()}</a></td>--%>
            <td><a href="/productline?command=lines&productline=${product.getProductLine()}">${product.getProductLine()}</a></td>
            <td>${product.getQuantity()}</td>
            <td><a href="/products?command=edit&id=${product.getProductCode()}"><button>Chỉnh sửa</button></a></td>
            <td><a href="/products?command=delete&id=${product.getProductCode()}"><button>Xóa</button></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>