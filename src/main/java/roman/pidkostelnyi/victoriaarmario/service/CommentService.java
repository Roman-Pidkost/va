package roman.pidkostelnyi.victoriaarmario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roman.pidkostelnyi.victoriaarmario.dto.request.CommentRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.CommentResponse;
import roman.pidkostelnyi.victoriaarmario.entity.Comment;
import roman.pidkostelnyi.victoriaarmario.repository.CommentRepository;
import roman.pidkostelnyi.victoriaarmario.tool.telegram.TelegramTool;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.KIEV_ZONE;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private TelegramTool telegramTool;

    public void save(CommentRequest request) {
        Comment comment = commentRepository.save(commentRequestToComment(null, request));
        telegramTool.sendCommentNotification(comment);
    }

    public List<CommentResponse> findAll() {
        return commentRepository.findAll().stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    public List<CommentResponse> findAllByProductId(Long productId) {
        return commentRepository.findAllByProductId(productId).stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    public List<CommentResponse> findAllToShowByProductId(Long productId) {
        return commentRepository.findAllByProductIdAndHiddenIsFalse(productId).stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    public void update(Long id, CommentRequest request) {
        commentRepository.save(commentRequestToComment(findOne(id), request));
    }

    public void delete(Long id) {
        commentRepository.delete(findOne(id));
    }

    public Comment findOne(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment with id " + id + " not exists"));
    }

    private Comment commentRequestToComment(Comment comment, CommentRequest request) {
        if (comment == null) {
            comment = new Comment();
            comment.setHidden(true);
            comment.setDatePosted(LocalDateTime.now(ZoneId.of(KIEV_ZONE)));
        } else {
            comment.setHidden(request.getHidden());
        }
        comment.setUsername(request.getUsername());
        comment.setText(request.getText());
        comment.setRating(request.getRating());
        comment.setProduct(productService.findOne(request.getProductId()));
        return comment;
    }
}
