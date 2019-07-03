package roman.pidkostelnyi.victoriaarmario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.pidkostelnyi.victoriaarmario.entity.Subcategory;

import java.util.stream.Stream;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Stream<Subcategory> findAllByCategoryId(Long categoryId);
}
