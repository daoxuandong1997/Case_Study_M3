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
<h1>Create a new product</h1>
<hr>
<h3>${requestScope["mess"]}</h3>
<h3><a href="/products?command=create_lines">Create a new product lines</a></h3>
<form method="post" style="border: 1px solid">
    <table>
        <tr>
            <th>ID</th>
            <td><input type="text" name="productcode" placeholder="Set Code"></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><input type="text" name="productname" placeholder="Set Name"></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><input type="text" name="price" placeholder="Set Price"></td>
        </tr>
        <tr>
            <th>Line</th>
            <td>
                <select name="" id="">
                    <c:forEach var="productLines" items="${productLines}">
                        <option value="${productLines.getProductLine()}">
                            <c:out value="${productLines.getProductLine()}"/>
                        </option>
                    </c:forEach>
                </select>
            </td>
<%--            <td><input type="text" name="productline" placeholder="Set Product Line"></td>--%>
        </tr>
        <tr>
            <th>Vendor</th>
            <td><input type="text" name="productvendor" placeholder="Set Product Vendor/address"></td>
        </tr>
        <tr>
            <th>Quantity</th>
            <td><input type="text" name="quantity" placeholder="Set Quantity"></td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <button type="submit">Create</button>
            </td>
        </tr>
    </table>
</form>
<h3><a href="/products">Back to product</a></h3>
</body>
</html>
