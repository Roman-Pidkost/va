package roman.pidkostelnyi.victoriaarmario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import roman.pidkostelnyi.victoriaarmario.entity.Color;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    @Query("from Color c left join c.products p where p.id=:productId")
    List<Color> findAllByProductId(@Param("productId") Long productId);
}
