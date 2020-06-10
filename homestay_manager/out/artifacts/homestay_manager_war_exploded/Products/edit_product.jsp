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
            <th>ID</th>
            <td><input name="productcode" value="${requestScope["product"].getProductCode()}" type="hidden" >${requestScope["product"].getProductCode()}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td><input type="text" name="productname"  value="${requestScope["product"].getProductName()}"></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><input type="text" name="price" value="${requestScope["product"].getPrice()}"></td>
        </tr>
        <tr>
            <th>Line</th>
            <td>
                <select name="productline" id="">
                    <c:forEach var="productLines" items="${productLines}">
                        <option value="${productLines.getProductLine()}">
                            <c:out value="${productLines.getProductLine()}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
<%--            <td><input type="text" name="productline" value="${requestScope["product"].getProductLine()}"></td>--%>
        </tr>
        <tr>
            <th>Vendor</th>
            <td><input type="text" name="productvendor" value="${requestScope["product"].getProductVendor()}"></td>
        </tr>
        <tr>
            <th>Quantity</th>
            <td><input type="text" name="quantity" value="${requestScope["product"].getQuantity()}"></td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <button type="submit">Update product</button>
            </td>
        </tr>
    </table>
</form>
<h3><a href="/products">Back to product list</a></h3>
</body>
</html>
