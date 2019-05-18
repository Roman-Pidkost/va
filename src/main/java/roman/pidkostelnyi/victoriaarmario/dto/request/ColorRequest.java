package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ColorRequest {
    @NotNull
    private String name;
    private String image;
}
