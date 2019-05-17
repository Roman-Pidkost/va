package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    @JsonProperty("subcategory")
    private SubcategoryResponse subcategoryResponse;
    private List<String> images;
    @JsonProperty("colors")
    private List<ColorResponse> colorResponses;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        subcategoryResponse = new SubcategoryResponse(product.getSubcategory());
        images = product.getImages();
        colorResponses = product.getColors().stream().map(ColorResponse::new).collect(Collectors.toList());
    }

}
