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
    private String catalogNumber;
    private String mainImage;
    private List<String> images;
    private String mainImage;
    @JsonProperty("subcategory")
    private SubcategoryResponse subcategoryResponse;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        catalogNumber = product.getCatalogNumber();
        mainImage = product.getMainImage();
        images = product.getImages();
        mainImage = product.getMainImage();
        subcategoryResponse = new SubcategoryResponse(product.getSubcategory());
    }

}
