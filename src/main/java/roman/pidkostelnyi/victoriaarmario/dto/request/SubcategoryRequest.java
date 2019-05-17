package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubcategoryRequest {
    private String name;
    private String image;
    private Long categoryId;
}
