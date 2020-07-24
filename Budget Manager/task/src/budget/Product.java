package budget;

public abstract class Product {
    private final String productName;
    private final double productPrice;
    private final String productCategory;

    public Product(String productName, double productPrice, String productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    @Override
    public String toString() {

        return String.format(productName + " $%.2f", productPrice);
    }
}

class Food extends Product {

    public Food(String productName, double productPrice, String productCategory) {
        super(productName, productPrice, productCategory);
    }
}

class Clothes extends Product {

    public Clothes(String productName, double productPrice, String productCategory) {
        super(productName, productPrice, productCategory);
    }
}

class Entertainment extends Product {

    public Entertainment(String productName, double productPrice, String productCategory) {
        super(productName, productPrice, productCategory);
    }
}

class Other extends Product {

    public Other(String productName, double productPrice, String productCategory) {
        super(productName, productPrice, productCategory);
    }
}