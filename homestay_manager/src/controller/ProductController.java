package controller;

import model.product.Product;
import model.product.ProductDao;
import model.product.ProductLine;
import utils.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
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
            case "create_line":
                createProductLine(request,response);
                break;
            case "editLine":
                updateProductLine(request,response);
                break;
            default:
                getList(request,response);
                break;
        }
    }

    private void updateProductLine(HttpServletRequest request, HttpServletResponse response) {
        String product_line = request.getParameter("productline");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        ProductLine productLine = new ProductLine(product_line,description,image);

        this.productDao.updateProductLine(productLine);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Products/edit_product_line.jsp");
        request.setAttribute("mess","Productline was updated");

        try{
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteProductById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            this.productDao.deleteByid(id);
            getList(request,response);
        }catch (Exception e) {
            response.getWriter().write("Loi khi xoa san pham co id la: " + id);
        }
    }

    private void createProductLine(HttpServletRequest request, HttpServletResponse response) {
        String product_line = request.getParameter("line");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        ProductLine productLine = new ProductLine(product_line,description,image);

        productDao.saveProductLine(productLine);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Products/create_lines.jsp");
        request.setAttribute("mess","A new product line was created");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getList(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = this.productDao.getAllList();
        request.setAttribute("products",products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Products/product_list.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("productcode"));
        String name = request.getParameter("productname");
        float price = Float.parseFloat(request.getParameter("price"));
        String productline = request.getParameter("productline");
        String productvendor = request.getParameter("productvendor");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        List<ProductLine> productLines = this.productDao.getProductLine();

        request.setAttribute("productLines", productLines);

        Product product = this.productDao.findById(id);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            product.setProductName(name);
            product.setPrice(price);
            product.setProductLine(productline);
            product.setProductVendor(productvendor);
            product.setQuantity(quantity);

            this.productDao.update(id,product);
            request.setAttribute("product", product);
            request.setAttribute("mess", "Product information was updated");
            dispatcher = request.getRequestDispatcher("Products/edit_product.jsp");
        }
        try{
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("productcode"));
        String name = request.getParameter("productname");
        float price = Float.parseFloat(request.getParameter("price"));
        String productline = request.getParameter("productline");
        String productvendor = request.getParameter("productvendor");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        List<ProductLine> productLines = this.productDao.getProductLine();

        request.setAttribute("productLines", productLines);

        Product product = new Product();
        product.setProductCode(id);
        product.setProductName(name);
        product.setPrice(price);
        product.setProductLine(productline);
        product.setProductVendor(productvendor);
        product.setQuantity(quantity);

        productDao.save(product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Products/create_list.jsp");
        request.setAttribute("mess","A new product was created");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //====================================================================



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
            case "create_lines":
                showCreateLinesForm(request,response);
                break;
            case"lines":
                showProductLine(request,response);
                break;
            case "delete":
                deleteProductById(request,response);
                break;
            case "showLines":
                showAllProductLines(request,response);
                break;
            case "editLine":
                showEditProductLine(request,response);
                break;
            case "deleteLine":
                deleteProductLine(request,response);
                break;
            default:
                getList(request,response);
                break;
        }
        response.setContentType("text/html;charset=UTF-8");

    }

    private void deleteProductLine(HttpServletRequest request, HttpServletResponse response) {
        String productLine = request.getParameter("productline");
        try{
            this.productDao.deleteByProductLine(productLine);
            showAllProductLines(request,response);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    private void showEditProductLine(HttpServletRequest request, HttpServletResponse response) {
        String product_line = request.getParameter("productline");
        ProductLine productLine = this.productDao.findByProductLine(product_line);
        RequestDispatcher dispatcher;
        if (productLine == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("productline",productLine);
            dispatcher = request.getRequestDispatcher("Products/edit_product_line.jsp");
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showProductLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String product_line = request.getParameter("productline");
            ProductLine productLine = this.productDao.getInforLine(product_line);
           request.setAttribute("productLine", productLine);
           RequestDispatcher dispatcher = request.getRequestDispatcher("Products/product_lines.jsp");

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateLinesForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Products/create_lines.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productDao.findById(id);
        List<ProductLine> productLines = this.productDao.getProductLine();
        request.setAttribute("productLines", productLines);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product",product);
            dispatcher = request.getRequestDispatcher("Products/edit_product.jsp");
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        List<ProductLine> productLines = this.productDao.getProductLine();

        request.setAttribute("productLines", productLines);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Products/create_list.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllProductLines(HttpServletRequest request, HttpServletResponse response){
        List<ProductLine> productLines = this.productDao.getAllListLine();
        request.setAttribute("listLine",productLines);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Products/ShowAllProductLines.jsp");

        try{
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
