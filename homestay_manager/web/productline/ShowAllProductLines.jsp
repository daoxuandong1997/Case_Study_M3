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
    <a href="/productline?command=create">Tạo 1 loại phòng mới</a>
</p>
<table border="1px">
    <tr>
        <th>Loại Phòng</th>
        <th>Mô tả</th>
        <th>Hình ảnh</th>
    </tr>
    <c:forEach var="productLines" items="${requestScope['listLine']}">
        <tr>
            <td><c:out value="${productLines.getProductLine()}"/></td>
            <td><c:out value= "${productLines.getDescription()}"/></td>
            <td><img src="<c:out value="${productLines.getImage()}"/>" height="500px"></td>
            <td><a href="/productline?command=edit&productline=${productLines.getProductLine()}"><button>Chỉnh sửa</button></a></td>
            <td><a href="/productline?command=delete&productline=${productLines.getProductLine()}"><button>Xóa</button></a></td>
        </tr>
    </c:forEach>
</table>
<h3><a href="/products">Back to product</a></h3>
</body>
</html>
