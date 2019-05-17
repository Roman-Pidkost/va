package roman.pidkostelnyi.victoriaarmario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.pidkostelnyi.victoriaarmario.entity.ProductForOrder;

@Repository
public interface ProductForOrderRepository extends JpaRepository<ProductForOrder, Long> {
}
