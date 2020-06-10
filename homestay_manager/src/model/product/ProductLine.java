package model.product;

public class ProductLine {
    private String productLine;
    private String description;
    private String image;

    private static ProductLine productLines;

    public static ProductLine getInstance(){
        if (productLines == null){
            productLines = new ProductLine();
        }
        return productLines;
    }

    private ProductLine() {
    }

    public ProductLine(String productLine, String description, String image) {
        this.productLine = productLine;
        this.description = description;
        this.image = image;
    }

    public ProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getDescription() {
        return description;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
