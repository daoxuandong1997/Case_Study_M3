package model.product;

import model.IDAO;
import model.product.Product;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IDAO<Product> {
    private DBConnection connection;

    public ProductDao(DBConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> getAllList() {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try{
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                int product_code = rs.getInt("productcode");
                String product_line = rs.getString("productline");
                String product_name = rs.getString("productname");
                String product_vendor = rs.getString("productvendor");
                int product_quantity = rs.getInt("productquantity");
                float product_price = rs.getFloat("productprice");

                Product product = new Product(product_code,product_name,product_price,product_quantity,product_line,product_vendor);
                products.add(product);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public ProductLine getInforLine(String product_line){

        String sql = "SELECT * FROM productLines WHERE productline = ?";
        ProductLine productLine = null;
        try {
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1,product_line);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String line = rs.getString("productline");
                String description = rs.getString("description");
                String image = rs.getString("image");

                productLine = new ProductLine(line,description,image);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productLine;

    }

    public List<ProductLine> getProductLine(){

        String sql = "SELECT productline FROM productlines";
        List<ProductLine> productLines = new ArrayList<>();
        try{
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);


            while (rs.next()){
                String product_line = rs.getString("productline");

                ProductLine productLine = new ProductLine(product_line);

                productLines.add(productLine);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productLines;
    }

    @Override
    public void save(Product product) {
        int product_code = product.getProductCode();
        String product_line = product.getProductLine();
        String product_name = product.getProductName();
        String product_vendor = product.getProductVendor();
        int product_quantity = product.getQuantity();
        float product_price = product.getPrice();

        String sql = "INSERT INTO products (productcode,productline,productname,productvendor,productquantity,productprice) VALUES (?,?,?,?,?,?)";

        try{

            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setInt(1,product_code);
            ps.setString(2,product_line);
            ps.setString(3,product_name);
            ps.setString(4,product_vendor);
            ps.setInt(5,product_quantity);
            ps.setFloat(6,product_price);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveProductLine(ProductLine productLine){
        String product_line = productLine.getProductLine();
        String description = productLine.getDescription();
        String image = productLine.getImage();

        String sql = "INSERT INTO productlines (productline, description,image) values(?,?,?)";

        try{
            PreparedStatement ps1 = this.connection.getConnection().prepareStatement(sql);
            ps1.setString(1,product_line);
            ps1.setString(2,description);
            ps1.setString(3,image);
            ps1.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteByid(int id) {
        String sql = "DELETE FROM products WHERE productcode = ?";
        try{
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, Product product) {
        int product_code = product.getProductCode();
        String product_line = product.getProductLine();
        String product_name = product.getProductName();
        String product_vendor = product.getProductVendor();
        int product_quantity = product.getQuantity();
        float product_price = product.getPrice();

        String sql = "UPDATE products SET productname = ?, productline = ?, productprice = ?, productvendor = ?, productquantity = ? WHERE productcode = ?";
        try{
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1,product_name);
            ps.setString(2,product_line);
            ps.setFloat(3,product_price);
            ps.setString(4,product_vendor);
            ps.setInt(5,product_quantity);
            ps.setInt(6,product_code);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override


    public Product findById(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE productcode = ?";
        try{
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int product_code = rs.getInt("productcode");
                String product_line = rs.getString("productline");
                String product_name = rs.getString("productname");
                String product_vendor = rs.getString("productvendor");
                int product_quantity = rs.getInt("productquantity");
                float product_price = rs.getFloat("productprice");

                product = new Product(product_code,product_name,product_price,product_quantity,product_line,product_vendor);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }
}
