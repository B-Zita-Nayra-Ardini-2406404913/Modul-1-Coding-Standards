package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb5589f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    // Positive Tests - Create
    @Test
    void testCreateProductPage_shouldReturnCorrectViewName() {
        String viewName = productController.createProductPage(model);

        assertEquals("createProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost_shouldCallServiceAndRedirect() {
        when(productService.create(any(Product.class))).thenReturn(product);

        String viewName = productController.createProductPost(product, model);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).create(product);
    }

    // Positive Tests - List
    @Test
    void testProductListPage_shouldReturnCorrectViewName() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);

        assertEquals("productList", viewName);
        verify(productService, times(1)).findAll();
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    void testProductListPage_shouldAddProductsToModel() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.findAll()).thenReturn(productList);

        productController.productListPage(model);

        verify(model, times(1)).addAttribute("products", productList);
    }

    // Positive Tests - Delete
    @Test
    void testDeleteProduct_shouldCallServiceAndRedirect() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.delete(productId)).thenReturn(product);

        String viewName = productController.deleteProduct(productId);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).delete(productId);
    }

    @Test
    void testDeleteProduct_shouldCallDeleteWithCorrectId() {
        String productId = "test-product-id";
        when(productService.delete(productId)).thenReturn(product);

        productController.deleteProduct(productId);

        verify(productService, times(1)).delete(productId);
    }

    // Positive Tests - Edit
    @Test
    void testEditProductPage_shouldReturnCorrectViewName() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product);

        String viewName = productController.editProductPage(productId, model);

        assertEquals("editProduct", viewName);
        verify(productService, times(1)).findById(productId);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_shouldAddProductToModel() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product);

        productController.editProductPage(productId, model);

        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPage_shouldFindProductById() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product);

        productController.editProductPage(productId, model);

        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testEditProductPost_shouldCallServiceAndRedirect() {
        when(productService.edit(product)).thenReturn(product);

        String viewName = productController.editProductPost(product, model);

        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).edit(product);
    }

    @Test
    void testEditProductPost_shouldCallEditWithCorrectProduct() {
        when(productService.edit(product)).thenReturn(product);

        productController.editProductPost(product, model);

        verify(productService, times(1)).edit(product);
    }

    // Negative Tests - Create
    @Test
    void testCreateProductPage_shouldNotReturnWrongViewName() {
        String viewName = productController.createProductPage(model);

        assertNotEquals("productList", viewName);
        assertNotEquals("wrongView", viewName);
    }

    @Test
    void testCreateProductPost_shouldNotReturnWrongRedirect() {
        when(productService.create(any(Product.class))).thenReturn(product);

        String viewName = productController.createProductPost(product, model);

        assertNotEquals("redirect:create", viewName);
        assertNotEquals("createProduct", viewName);
    }

    // Negative Tests - List
    @Test
    void testProductListPage_shouldNotReturnWrongViewName() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);

        assertNotEquals("createProduct", viewName);
        assertNotEquals("wrongView", viewName);
    }

    @Test
    void testProductListPage_withEmptyList_shouldStillWork() {
        List<Product> emptyList = new ArrayList<>();
        when(productService.findAll()).thenReturn(emptyList);

        String viewName = productController.productListPage(model);

        assertEquals("productList", viewName);
        verify(model, times(1)).addAttribute("products", emptyList);
    }

    // Negative Tests - Delete
    @Test
    void testDeleteProduct_shouldNotReturnWrongRedirect() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.delete(productId)).thenReturn(product);

        String viewName = productController.deleteProduct(productId);

        assertNotEquals("redirect:/product/create", viewName);
        assertNotEquals("redirect:/product/edit", viewName);
        assertNotEquals("deleteProduct", viewName);
    }

    @Test
    void testDeleteProduct_shouldNotCallServiceMultipleTimes() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.delete(productId)).thenReturn(product);

        productController.deleteProduct(productId);

        verify(productService, times(1)).delete(productId);
        verify(productService, atMostOnce()).delete(productId);
    }

    // Negative Tests - Edit
    @Test
    void testEditProductPage_shouldNotReturnWrongViewName() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product);

        String viewName = productController.editProductPage(productId, model);

        assertNotEquals("createProduct", viewName);
        assertNotEquals("productList", viewName);
        assertNotEquals("wrongView", viewName);
    }

    @Test
    void testEditProductPage_shouldNotCallFindByIdMultipleTimes() {
        String productId = "eb5589f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product);

        productController.editProductPage(productId, model);

        verify(productService, times(1)).findById(productId);
        verify(productService, atMostOnce()).findById(productId);
    }

    @Test
    void testEditProductPost_shouldNotReturnWrongRedirect() {
        when(productService.edit(product)).thenReturn(product);

        String viewName = productController.editProductPost(product, model);

        assertNotEquals("redirect:/product/create", viewName);
        assertNotEquals("editProduct", viewName);
        assertNotEquals("redirect:/product/edit", viewName);
    }

    @Test
    void testEditProductPost_shouldNotCallServiceMultipleTimes() {
        when(productService.edit(product)).thenReturn(product);

        productController.editProductPost(product, model);

        verify(productService, times(1)).edit(product);
        verify(productService, atMostOnce()).edit(product);
    }

    @Test
    void testEditProductPost_withNullProduct_shouldStillCallService() {
        Product nullProduct = null;
        when(productService.edit(any())).thenReturn(product);

        productController.editProductPost(nullProduct, model);

        verify(productService, times(1)).edit(nullProduct);
    }
}