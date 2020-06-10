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
    <a href="/products?command=create_line">Create a new product line</a>
</p>
<table border="1px">
    <tr>
        <th>ProductLine</th>
        <th>Description</th>
        <th>Image</th>
    </tr>
    <c:forEach var="productLines" items="${requestScope['listLine']}">
        <tr>
            <td><c:out value="${productLines.getProductLine()}"/></td>
            <td><c:out value= "${productLines.getDescription()}"/></td>
            <td><c:out value="${productLines.getImage()}"/></td>
            <td><a href="/products?command=edit&productline=${productline.getProductLine()}"><button>Edit</button></a></td>
            <td><a href="/products?command=delete&productline=${productline.getProductLine()}"><button>Delete</button></a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
