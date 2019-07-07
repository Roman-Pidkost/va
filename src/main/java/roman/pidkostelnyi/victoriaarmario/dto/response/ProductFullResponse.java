package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import roman.pidkostelnyi.victoriaarmario.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.DEFAULT_RATING;

@Getter
@Setter
public class ProductFullResponse {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private List<String> images;
    private String mainImage;
    @JsonProperty("colors")
    private List<ColorResponse> colorResponses;
    @JsonProperty("comments")
    private List<CommentResponse> commentResponses;
    private Double rating;

    public ProductFullResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        images = product.getImages();
        mainImage = product.getMainImage();
        colorResponses = product.getColors().stream().map(ColorResponse::new).collect(Collectors.toList());
        commentResponses = product.getComments().stream().map(CommentResponse::new).collect(Collectors.toList());
        rating = commentResponses.stream().mapToDouble(CommentResponse::getRating).average().orElse(DEFAULT_RATING);
    }
}
