package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Objects;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create (Product product){
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Product findById (String idToSearch) {
        Product result = null;
        for (Product p: productData){
            if (idToSearch.equals(p.getProductId())){
                result = p;
            }
        }
        return result;
    }

    public Product delete(String productId) {
        Product existingProduct = findById(productId);

        if (existingProduct != null) {
            productData.remove(existingProduct);
            return existingProduct;
        }

        throw new RuntimeException("Product with ID " + productId + " not found");
    }

    public Product edit(Product product) {
        Product existingProduct = findById(product.getProductId());

        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductQuantity(product.getProductQuantity());
            return existingProduct;
        }

        throw new RuntimeException("Product with ID " + product.getProductId() + " not found");
    }


    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}
