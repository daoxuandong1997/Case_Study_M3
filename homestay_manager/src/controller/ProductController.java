package controller;

import model.product.Product;
import model.product.ProductDao;
import model.product.productline.ProductLine;
import utils.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.IOException;
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
            case "delete":
                deleteProductById(request,response);
                break;
            default:
                getList(request,response);
                break;
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("Products/create_product.jsp");
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
            case "delete":
                showDeleteForm(request,response);
                break;
            default:
                getList(request,response);
                break;
        }
        response.setContentType("text/html;charset=UTF-8");

    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Products/delete_product.jsp");
        request.setAttribute("id",id);
        try{
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Products/create_product.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

//    private void getProducts(HttpServletRequest request, HttpServletResponse response){
//        List<ProductLine> productLines = this.productDao.getAllListLine();
//        request.setAttribute("listLine",productLines);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
//
//        try{
//            dispatcher.forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
