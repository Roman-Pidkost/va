package roman.pidkostelnyi.victoriaarmario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.pidkostelnyi.victoriaarmario.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}