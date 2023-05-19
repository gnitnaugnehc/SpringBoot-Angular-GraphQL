package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByName(List<String> tags);

}
