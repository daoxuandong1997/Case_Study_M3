package model.product.productline;

import model.IDAO;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductlineDAO implements IDAO<ProductLine> {
    private DBConnection connection;

    public ProductlineDAO(DBConnection connection) {
        this.connection = connection;
    }


    @Override
    public List<ProductLine> getAllList() {
        String sql = "SELECT * FROM productlines";
        List<ProductLine> productLines = new ArrayList<>();
        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String product_line = rs.getString("productline");
                String description = rs.getString("description");
                String image = rs.getString("image");

                ProductLine productLine = new ProductLine(product_line,description,image);

                productLines.add(productLine);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productLines;
    }

    @Override
    public void save(ProductLine productLine) {
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

    }

    @Override
    public void update(int id, ProductLine productLine) {

    }

    public void update(ProductLine productLine) {
        String product_line = productLine.getProductLine();
        String description = productLine.getDescription();
        String image = productLine.getImage();

        String sql = "UPDATE productlines SET productline = ?, description = ?, image = ? WHERE productline = ?";
        try{
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1,product_line);
            ps.setString(2,description);
            ps.setString(3,image);
            ps.setString(4,product_line);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ProductLine findById(int id) {
        return null;
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

    public void deleteByProductLine(String productLine){
        String sql1 = "DELETE FROM productlines WHERE productline = ?";
        String sql = "DELETE FROM products WHERE productline = ?";
        try{
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1,productLine);
            ps.execute();
            ps = this.connection.getConnection().prepareStatement(sql1);
            ps.setString(1,productLine);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ProductLine findByProductLine(String product_line){
        ProductLine productLine = null;
        String sql = "SELECT * FROM productlines WHERE productline = ?";
        try{
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1,product_line);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String _productLine = rs.getString("productline");
                String description = rs.getString("description");
                String image = rs.getString("image");

                productLine = new ProductLine(_productLine,description,image);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productLine;
    }

}
