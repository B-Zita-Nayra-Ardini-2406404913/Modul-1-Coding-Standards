package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Product {

    private String productId;
    private String productName;
    private int productQuantity;

    public abstract String getProductType();
}