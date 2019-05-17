package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Subcategory;

@Getter
@Setter
public class SubcategoryResponse {
    private Long id;
    private String name;
    private String image;
    @JsonProperty("category")
    private CategoryResponse categoryResponse;

    public SubcategoryResponse(Subcategory subcategory) {
        id = subcategory.getId();
        name = subcategory.getName();
        image = subcategory.getImage();
        categoryResponse = new CategoryResponse(subcategory.getCategory());
    }
}
