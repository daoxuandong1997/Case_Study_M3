package controller;

import model.Product;
import model.ProductDao;
import utils.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/productlist")
public class ProductController extends HttpServlet {

    private DBConnection connection = DBConnection.getInstance();
    private ProductDao productDao;

    public void init() throws ServletException {
        super.init();
        productDao = new ProductDao(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");

        if (command == null){
            command = "";
        }
        switch (command){
            case "create":
                createProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            default:
                getList(request,response);
                break;
        }
    }

    private void getList(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = this.productDao.getAllList();
        request.setAttribute("products",products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product_list.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("productcode"));
        String name = request.getParameter("productname");
        int price = Integer.parseInt(request.getParameter("price"));
        String productline = request.getParameter("productline");
        String productvendor = request.getParameter("productvendor");
        String quantity = request.getParameter("quantity");

        Product product = new Product();
        product.setProductCode(id);
        product.setProductName(name);
        product.setPrice(price);
        product.setProductLine(productline);
        product.setProductVendor(productvendor);

        productDao.save(product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/create_list.jsp");
        request.setAttribute("mess","A new product was created");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null){
            command = "";
        }
        switch (command){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            default:
                getList(request,response);
                break;
        }
        response.setContentType("text/html;charset=UTF-8");

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productDao.findById(id);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product",product);
            dispatcher = request.getRequestDispatcher("edit_product.jsp");
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create_list.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


}
