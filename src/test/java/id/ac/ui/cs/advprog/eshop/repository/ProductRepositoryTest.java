package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    // Existing Tests
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb5589f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb5589f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9e4c6-9b1a-437d-a0bf-d0821de90945");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    // FindById Tests
    @Test
    void testFindById_productExists_shouldReturnProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Product createdProduct = productRepository.create(product);
        String productId = createdProduct.getProductId();  // Ambil ID yang di-generate

        Product foundProduct = productRepository.findById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getProductId());
        assertEquals("Sampo Cap Bambang", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

    @Test
    void testFindById_productNotExists_shouldReturnNull() {
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Existing Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("non-existing-id");

        assertNull(foundProduct);
    }

    // Delete Tests
    @Test
    void testDelete_productExists_shouldDeleteAndReturnProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Product createdProduct = productRepository.create(product);
        String productId = createdProduct.getProductId();

        Product deletedProduct = productRepository.delete(productId);

        assertNotNull(deletedProduct);
        assertEquals(productId, deletedProduct.getProductId());
        assertEquals("Sampo Cap Bambang", deletedProduct.getProductName());

        Product foundProduct = productRepository.findById(productId);
        assertNull(foundProduct);
    }
    @Test
    void testDelete_productNotExists_shouldThrowException() {
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Existing Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productRepository.delete("non-existing-id");
        });

        assertEquals("Product with ID non-existing-id not found", exception.getMessage());
    }

    // Edit Tests
    @Test
    void testEdit_productExists_shouldUpdateAndReturnProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Product createdProduct = productRepository.create(product);
        String productId = createdProduct.getProductId();

        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("Sampo Cap Bambang Updated");
        updatedProduct.setProductQuantity(150);

        Product editedProduct = productRepository.edit(updatedProduct);

        assertNotNull(editedProduct);
        assertEquals(productId, editedProduct.getProductId());
        assertEquals("Sampo Cap Bambang Updated", editedProduct.getProductName());
        assertEquals(150, editedProduct.getProductQuantity());
    }

    @Test
    void testEdit_productNotExists_shouldThrowException() {
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Existing Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existing-id");
        updatedProduct.setProductName("Non Existing Product");
        updatedProduct.setProductQuantity(200);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productRepository.edit(updatedProduct);
        });

        assertEquals("Product with ID non-existing-id not found", exception.getMessage());
    }
}