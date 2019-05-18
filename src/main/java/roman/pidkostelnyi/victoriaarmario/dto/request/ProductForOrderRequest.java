package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductForOrderRequest {
    @NotNull
    private Long productId;
    @NotNull
    private Integer count;
}
