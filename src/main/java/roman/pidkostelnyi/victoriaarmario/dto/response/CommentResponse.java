package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {

    private Long id;
    private String username;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datePosted;
    private Boolean show;

    @JsonProperty("product")
    private ProductResponse productResponse;

    public CommentResponse(Comment comment) {
        id = comment.getId();
        username = comment.getUsername();
        text = comment.getText();
        datePosted = comment.getDatePosted();
        productResponse = new ProductResponse(comment.getProduct());
        show = comment.getShow();
    }
}
