<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daoxu
  Date: 6/4/2020
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <style>
        th{
            text-align: left;
        }
    </style>
</head>
<body>
<h1>Product information</h1>
<h3>${requestScope["mess"]}</h3>
<hr>
<form method="post" style="border: 1px solid">
    <table>
        <tr>
            <th>Line</th>
            <td>
                <input type="text" name="productline" value="${requestScope["productline"].getProductLine()}">
            </td>
            <%--            <td><input type="text" name="productline" value="${requestScope["product"].getProductLine()}"></td>--%>
        </tr>
        <tr>
            <th>Description</th>
            <td><input type="text" name="description" value="${requestScope["productline"].getDescription()}"></td>
        </tr>
        <tr>
            <th>Image</th>
            <td><input type="text" name="image" value="${requestScope["productline"].getImage()}"></td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <button type="submit">Update productline</button>
            </td>
        </tr>
    </table>
</form>
<h3><a href="/products">Back to product list</a></h3>
</body>
</html>
