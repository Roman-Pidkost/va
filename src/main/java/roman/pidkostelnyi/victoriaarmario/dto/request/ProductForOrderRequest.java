package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForOrderRequest {
    private Long productId;
    private Integer count;
}
