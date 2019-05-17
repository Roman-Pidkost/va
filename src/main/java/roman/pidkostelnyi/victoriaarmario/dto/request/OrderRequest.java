package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String phoneNumber;
    private String email;
    private String address;
    private String comment;
    private List<ProductForOrderRequest> products;
}
