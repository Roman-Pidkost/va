package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    private String email;
    private String address;
    private String comment;

    @NotEmpty
    private List<ProductForOrderRequest> products;
}
