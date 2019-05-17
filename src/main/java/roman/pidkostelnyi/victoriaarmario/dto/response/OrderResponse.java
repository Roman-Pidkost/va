package roman.pidkostelnyi.victoriaarmario.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Order;
import roman.pidkostelnyi.victoriaarmario.entity.ProductForOrder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String phoneNumber;
    private String email;
    private String address;
    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime posted;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime done;

    @JsonProperty("products")
    private List<ProductForOrderResponse> productForOrderResponses;

    public OrderResponse(Order order) {
        id = order.getId();
        phoneNumber = order.getPhoneNumber();
        email = order.getEmail();
        address = order.getAddress();
        comment = order.getComment();
        posted = order.getPosted();
        done = order.getDone();
        productForOrderResponses = order.getProductsForOrder().stream().map(ProductForOrderResponse::new).collect(Collectors.toList());
    }
}
