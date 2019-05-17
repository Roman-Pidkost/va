package roman.pidkostelnyi.victoriaarmario.dto.response;

import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Category;

@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private String image;

    public CategoryResponse(Category category) {
        id = category.getId();
        name = category.getName();
        image = category.getImage();
    }
}
