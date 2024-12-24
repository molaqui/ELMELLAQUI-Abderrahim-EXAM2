package org.example.productservice.web;

import org.example.productservice.entity.Product;
import org.example.productservice.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class ProductGraphQLController {
    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        return productService.getProductById(id);
    }

    @MutationMapping
    public Product addProduct(@Argument String name, @Argument String description,
                              @Argument double price, @Argument int stock) {
        return productService.addProduct(
                Product.builder().name(name).description(description).price(price).stock(stock).build()
        );
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument String name,
                                 @Argument String description, @Argument double price,
                                 @Argument int stock) {
        return productService.updateProduct(
                id, Product.builder().name(name).description(description).price(price).stock(stock).build()
        );
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Long id) {
        return productService.deleteProduct(id);
    }
}
