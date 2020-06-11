package controller;

import model.product.ProductDao;
import model.product.productline.ProductLine;
import model.product.productline.ProductlineDAO;
import utils.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductlineController", urlPatterns = "/productline")
public class ProductlineController extends HttpServlet {
    private DBConnection connection = DBConnection.getInstance();
    private ProductlineDAO productlineDAO;

    public void init() throws ServletException {
        super.init();
        productlineDAO = new ProductlineDAO(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");

        if (command == null) {
            command = "";
        }
        switch (command) {
            case "create":
                createProductLine(request, response);
                break;
            case "edit":
                updateProductLine(request, response);
                break;
            case "delete":
                deleteProductLine(request,response);
                break;
            default:
                showAllProductLines(request, response);
                break;
        }
    }

    private void showAllProductLines(HttpServletRequest request, HttpServletResponse response) {
        List<ProductLine> productLines = this.productlineDAO.getAllList();
        request.setAttribute("listLine", productLines);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productline/ShowAllProductLines.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProductLine(HttpServletRequest request, HttpServletResponse response) {
        String product_line = request.getParameter("productline");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        ProductLine productLine = new ProductLine(product_line, description, image);

        this.productlineDAO.update(productLine);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Products/edit_product_line.jsp");
        request.setAttribute("mess", "Productline was updated");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createProductLine(HttpServletRequest request, HttpServletResponse response) {
        String product_line = request.getParameter("line");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        ProductLine productLine = new ProductLine(product_line, description, image);

        productlineDAO.save(productLine);

        RequestDispatcher dispatcher = request.getRequestDispatcher("productline/create_lines.jsp");
        request.setAttribute("mess", "A new product line was created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteProductLine(HttpServletRequest request, HttpServletResponse response) {
        String productLine = request.getParameter("productline");
        try {
            this.productlineDAO.deleteByProductLine(productLine);
            showAllProductLines(request, response);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");

        if (command == null) {
            command = "";
        }
        switch (command) {
            case "edit":
                showEditProductLine(request, response);
                break;
            case "delete":
                showDeleteProductline(request, response);
                break;
            case "create":
                showCreateLinesForm(request,response);
                break;
            case"lines":
                showProductLine(request,response);
                break;
            default:
                showAllProductLines(request,response);
                break;
        }
    }

    private void showProductLine(HttpServletRequest request, HttpServletResponse response) {
        String productLine = request.getParameter("productline");
        ProductLine productLine1 = this.productlineDAO.getInforLine(productLine);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("productline/product_line.jsp");
        request.setAttribute("productline",productLine1);
        try{
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteProductline(HttpServletRequest request, HttpServletResponse response) {
        String productline = request.getParameter("productline");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("productline/delete_product_line.jsp");
        request.setAttribute("productline",productline);
        try{
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateLinesForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("productline/create_lines.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }



    private void showEditProductLine(HttpServletRequest request, HttpServletResponse response) {
        String product_line = request.getParameter("productline");
        ProductLine productLine = this.productlineDAO.findByProductLine(product_line);
        RequestDispatcher dispatcher;
        if (productLine == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("productline", productLine);
            dispatcher = request.getRequestDispatcher("Products/edit_product_line.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
