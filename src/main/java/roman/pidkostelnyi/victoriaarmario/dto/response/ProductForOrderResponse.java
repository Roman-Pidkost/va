package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.ProductForOrder;

@Getter
@Setter
public class ProductForOrderResponse {
    private Long id;
    private Integer count;
    @JsonProperty("product")
    private ProductResponse productResponse;

    public ProductForOrderResponse(ProductForOrder productForOrder) {
        id = productForOrder.getId();
        count = productForOrder.getCount();
        productResponse = new ProductResponse(productForOrder.getProduct());
    }
}
