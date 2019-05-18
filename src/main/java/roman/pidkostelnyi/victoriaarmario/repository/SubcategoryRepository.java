package roman.pidkostelnyi.victoriaarmario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.pidkostelnyi.victoriaarmario.entity.Subcategory;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    List<Subcategory> findAllByCategoryId(Long categoryId);
}
