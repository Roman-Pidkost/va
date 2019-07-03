package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Product;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private List<String> images;
    @JsonProperty("subcategory")
    private SubcategoryResponse subcategoryResponse;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        images = product.getImages();
        subcategoryResponse = new SubcategoryResponse(product.getSubcategory());
    }

}
