package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.DEFAULT_RATING;

@Getter
@Setter
public class ProductFindOneResponse {
    private Long id;
    private String name;
    private Integer price;
    private Double rating;
    private String description;
    private String catalogNumber;
    private String mainImage;
    private List<String> images;
    @JsonProperty("comments")
    private List<CommentResponse> commentResponses;
    @JsonProperty("colors")
    private List<ColorResponse> colorResponses;

    public ProductFindOneResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        catalogNumber = product.getCatalogNumber();
        mainImage = product.getMainImage();
        images = product.getImages();
        commentResponses = product.getComments().stream().filter(e -> !e.getHidden()).map(CommentResponse::new).collect(Collectors.toList());
        colorResponses = product.getColors().stream().map(ColorResponse::new).collect(Collectors.toList());
        rating = commentResponses.stream().mapToDouble(CommentResponse::getRating).average().orElse(DEFAULT_RATING);
    }
}
