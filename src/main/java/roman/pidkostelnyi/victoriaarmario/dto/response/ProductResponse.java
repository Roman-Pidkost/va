package roman.pidkostelnyi.victoriaarmario.dto.response;

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
    private String subcategory;
    private List<String> images;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        subcategory = product.getSubcategory().getName();
        images = product.getImages();
    }

}
