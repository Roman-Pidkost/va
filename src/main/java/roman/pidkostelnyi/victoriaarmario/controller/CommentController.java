package roman.pidkostelnyi.victoriaarmario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.pidkostelnyi.victoriaarmario.dto.request.CommentRequest;
import roman.pidkostelnyi.victoriaarmario.dto.response.CommentResponse;
import roman.pidkostelnyi.victoriaarmario.service.CommentService;

import javax.validation.Valid;

import java.util.List;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.COMMENT_URL;

@CrossOrigin
@RestController
@RequestMapping(COMMENT_URL)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public void create(@Valid @RequestBody CommentRequest request) {
        commentService.save(request);
    }

    @GetMapping()
    public List<CommentResponse> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/byProduct")
    public List<CommentResponse> findAllByProductId(Long productId) {
        return commentService.findAllByProductId(productId);
    }

    @GetMapping("/showByProduct")
    public List<CommentResponse> findAllToShowByProductId(Long productId) {
        return commentService.findAllToShowByProductId(productId);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody CommentRequest request) {
        commentService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        commentService.delete(id);
    }
}
