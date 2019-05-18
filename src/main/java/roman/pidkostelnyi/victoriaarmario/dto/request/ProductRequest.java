package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductRequest {
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    private String description;
    private List<String> images = new ArrayList<>();
    @NotNull
    private Long subcategoryId;
    private List<Long> colorsIds = new ArrayList<>();
}
