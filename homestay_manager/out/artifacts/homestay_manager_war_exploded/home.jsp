<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daoxu
  Date: 6/9/2020
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomeStay</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" integrity="sha256-KzZiKy0DWYsnwMF+X1DvQngQ2/FxF7MF3Ff72XcpuPs=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" integrity="sha256-h20CPZ0QyXlBuAw7A+KluUYx/3pK+c7lYEpqLTlxjYQ=" crossorigin="anonymous" />
    <link rel="stylesheet" href="home.css">

</head>
<body>

<div class="main">

    <div class="header">
        <div class="col-xs-10 col-md-6 is-flex align-center flex-nowrap">
            <div style="float: left;width: 40%">
                <nav class="navbar navbar-light bg-light">
                    <a class="navbar-brand" href="#">
                        <img src="image/logo-chim-cu-meo-16.jpg" width="50" height="50" class="d-inline-block align-top" alt=""
                             loading="lazy">
                        Nhà của cú
                    </a>
                </nav>
            </div>
            <div style="float: left;width: 60%">
                <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
                    </form>
                </nav>
            </div>
        </div>
        <div class="col-xs-2 end-xs col-md-6 ui-menu-item-wrapper">
            <ul class="nav justify-content-end">
                <li class="nav-item">
                    <a class="nav-link active" href="/login">Đăng nhập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signup.jsp">Đăng ký</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="head-link"></div>

    <div class="content">
        <div class="container-fluid">
            <div class="greeting">
                <div class="title">
                    <h3>Chào mừng đến với nhà của Cú !</h3>
                </div>
                <p>Đặt tổ để đôi chim có nơi về ngủ sau ngày dài mệt mỏi</p>
<%--                <p>--%>
<%--                    <a href="/login" style="text-decoration: none ">Đăng nhập</a>--%>
<%--                    <span class="text-lowercase">hoặc</span>--%>
<%--                    <a href="signup.jsp" style="text-decoration: none">Đăng ký</a>--%>
<%--                    để trải nghiệm--%>
<%--                </p>--%>
            </div>

        </div>

            <div class="row">
                <c:forEach items='${requestScope["listLine"]}' var="productline">

                        <div class="card-group col-sm-3" style="width: 18rem;">
                            <img class="card-img-top" src="${productline.getImage()}" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title"><c:out value="${productline.getProductLine()}"/></h5>
                                <p class="card-text"><c:out value="${productline.getDescription()}"/></p>
                                <a href="#" class="btn btn-primary">Đặt chỗ ngay</a>
                            </div>
                        </div>
                    </c:forEach>

            </div>

    </div>

    <div class="row footer">
        <div class="col-12">
            <div class="text-center mt-md-3">
                <p>&copy; BànCuối Corporation 2020. All right reserved</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
