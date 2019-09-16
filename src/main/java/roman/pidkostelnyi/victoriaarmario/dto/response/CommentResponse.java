package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime datePosted;
    private Boolean hidden;
    private Integer rating;

    public CommentResponse(Comment comment) {
        id = comment.getId();
        username = comment.getUsername();
        text = comment.getText();
        datePosted = comment.getDatePosted();
        hidden = comment.getHidden();
        rating = comment.getRating();
    }
}
