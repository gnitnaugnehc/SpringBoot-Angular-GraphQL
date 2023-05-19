package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Iterable<Product> findByTagsIn(List<String> tags);

}
