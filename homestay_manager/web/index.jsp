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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" integrity="sha256-h20CPZ0QyXlBuAw7A+KluUYx/3pK+c7lYEpqLTlxjYQ=" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" integrity="sha256-KzZiKy0DWYsnwMF+X1DvQngQ2/FxF7MF3Ff72XcpuPs=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="index.css">
  </head>
  <body>
  <div id="main">
    <div id="layout">
      <div class="header-wrap">
        <div class="header">
          <div class="container-fluid">
            <div class="row middle-xs">
              <div class="col-xs-10 col-md-6 is-flex align-center flex-nowrap">
                <div style="float: left;width: 20%" >
                  <button class="btn btn-success logo"><i class="fas fa-user-friends fa-2x"></i></button>
                </div>
                <div style="float: left;width: 80%">
                  <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                  </nav>
                </div>
              </div>
              <div class="col-xs-2 end-xs col-md-6 ui-menu-item-wrapper">
                <ul class="menu is-flex end-xs middle-xs">
                  <li class="menu_item"><a href="/login">Login</a></li>
                  <li class="menu_item"><a href="signup.jsp">SignUp</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </body>
</html>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
