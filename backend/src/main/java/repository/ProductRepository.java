package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Iterable<Product> findByTags(List<String> tags);

}
