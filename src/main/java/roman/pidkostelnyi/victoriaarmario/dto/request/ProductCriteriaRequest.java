package roman.pidkostelnyi.victoriaarmario.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCriteriaRequest {

    private String value;
    private Integer minPrice;
    private Integer maxPrice;
    private List<Long> colorsId;
    private Long categoryId;
    private Long subcategoryId;

    @JsonProperty("pagination")
    private PaginationRequest paginationRequest;
}
