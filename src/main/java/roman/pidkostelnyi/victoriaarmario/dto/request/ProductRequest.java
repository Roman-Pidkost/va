package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private Integer price;
    private String description;
    private List<String> images = new ArrayList<>();
    private Long subcategoryId;
    private List<Long> colorsIds = new ArrayList<>();
}
