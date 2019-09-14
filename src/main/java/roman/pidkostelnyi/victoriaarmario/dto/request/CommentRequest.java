package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentRequest {
    @NotNull
    private String username;
    @NotNull
    private String text;
    @NotNull
    private Double rating;
    @NotNull
    private Long productId;
    private Boolean hidden;
}
