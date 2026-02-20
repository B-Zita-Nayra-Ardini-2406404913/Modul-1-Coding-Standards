package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb5589f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    // Positive Tests
    @Test
    void testCreateMethod_exists() {
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product result = productService.create(product);

        assertNotNull(result);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllMethod_exists() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testCreateMethod_returnsProduct() {
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product result = productService.create(product);

        assertTrue(result instanceof Product);
    }

    @Test
    void testFindAllMethod_returnsList() {
        List<Product> productList = new ArrayList<>();
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertTrue(result instanceof List);
    }

    @Test
    void testCreate_shouldReturnSameProduct() {
        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertEquals(product, result);
        assertEquals("eb5589f-1c39-460e-8860-71af6af63bd6", result.getProductId());
        assertEquals("Sampo Cap Bambang", result.getProductName());
        assertEquals(100, result.getProductQuantity());
    }

    @Test
    void testFindAll_shouldReturnCorrectSize() {
        Product product2 = new Product();
        product2.setProductId("eb5589f-1c39-460e-8860-71af6af63bd7");
        product2.setProductName("Sampo Cap Usro");
        product2.setProductQuantity(50);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
    }

    // Negative Tests
    @Test
    void testCreateMethod_shouldNotReturnNull() {
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product result = productService.create(product);

        assertNotNull(result);
    }

    @Test
    void testFindAllMethod_shouldNotReturnNull() {
        List<Product> productList = new ArrayList<>();
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
    }

    @Test
    void testCreateMethod_shouldNotReturnWrongType() {
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Object result = productService.create(product);

        assertFalse(result instanceof String);
        assertFalse(result instanceof Integer);
    }

    @Test
    void testFindAllMethod_shouldNotReturnWrongType() {
        List<Product> productList = new ArrayList<>();
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        Object result = productService.findAll();

        assertFalse(result instanceof String);
        assertFalse(result instanceof Product);
    }

    @Test
    void testFindAll_withEmptyList_shouldReturnEmptyList() {
        List<Product> emptyList = new ArrayList<>();
        Iterator<Product> emptyIterator = emptyList.iterator();

        when(productRepository.findAll()).thenReturn(emptyIterator);

        List<Product> result = productService.findAll();

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreate_shouldNotReturnDifferentProduct() {
        Product differentProduct = new Product();
        differentProduct.setProductId("different-id");
        differentProduct.setProductName("Different Product");
        differentProduct.setProductQuantity(50);

        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertNotEquals(differentProduct.getProductId(), result.getProductId());
        assertNotEquals(differentProduct.getProductName(), result.getProductName());
        assertNotEquals(differentProduct.getProductQuantity(), result.getProductQuantity());
    }
}