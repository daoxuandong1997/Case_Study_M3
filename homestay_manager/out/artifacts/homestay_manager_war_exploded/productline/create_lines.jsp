<%--
  Created by IntelliJ IDEA.
  User: daoxu
  Date: 6/10/2020
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create a new product line</h1>
<hr>
<h3>${requestScope["mess"]}</h3>
<form method="post" style="border: 1px solid" action="/productline?command=create">
    <table>
        <tr>
            <th>Product line</th>
            <td><input type="text" name="line" placeholder="Set product line"></td>
        </tr>
        <tr>
            <th>Desciption</th>
            <td><input type="text" name="description" placeholder="Set description"></td>
        </tr>
        <tr>
            <th>image</th>
            <td><input type="text" name="image" placeholder="link image"></td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <button type="submit">Create</button>
            </td>
        </tr>
    </table>
</form>
<h3><a href="/productline">Back to product</a></h3>
</body>
</html>
