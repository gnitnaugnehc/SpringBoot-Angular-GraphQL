package application.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import application.model.Product;
import application.model.Tag;
import application.repository.ProductRepository;
import application.repository.TagRepository;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    public ProductController(ProductRepository productRepository, TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
    }

    @QueryMapping
    Iterable<Product> products() {
        return productRepository.findAll();
    }

    @QueryMapping
    Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @QueryMapping
    Iterable<Product> searchProductsByTags(List<String> tags) {
        return productRepository.findByTagsIn(tags);
    }

    @MutationMapping
    Product createProduct(String name, String description, Double price, List<String> tags) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        List<Tag> tagList = tagRepository.findByNameIn(tags);
        product.setTags(tagList);

        return productRepository.save(product);
    }

    @MutationMapping
    Product updateProduct(Long id, String name, String description, Double price, List<String> tags) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            if (name != null) {
                product.setName(name);
            }
            if (description != null) {
                product.setDescription(description);
            }
            if (price != null) {
                product.setPrice(price);
            }
            if (tags != null) {
                List<Tag> tagList = tagRepository.findByNameIn(tags);
                product.setTags(tagList);
            }

            return productRepository.save(product);
        }

        return null;
    }

    @MutationMapping
    Boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }
}