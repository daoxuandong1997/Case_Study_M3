package controller;

import model.user.User;
import model.user.UserDAO;
import utils.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserServlet",urlPatterns = "/login")
public class UserController extends HttpServlet {
    DBConnection dbConnection = DBConnection.getInstance();
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(dbConnection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.getByUsername(username);

        if (user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("IS_LOGGINED", true);
            session.setAttribute("ROLE", user.getRole());


            response.sendRedirect("/products");
        }else {

            // thong bao loi dang nhap

            request.setAttribute("message", "Đăng nhập không thành công");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);

    }
}
