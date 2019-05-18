package roman.pidkostelnyi.victoriaarmario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.pidkostelnyi.victoriaarmario.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProductId(Long productId);
    List<Comment> findAllByProductIdAndShowIsTrue(Long productId);
}
