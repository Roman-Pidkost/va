package roman.pidkostelnyi.victoriaarmario.dto.response;

import lombok.Getter;
import lombok.Setter;
import roman.pidkostelnyi.victoriaarmario.entity.Color;

@Getter
@Setter
public class ColorResponse {
    private Long id;
    private String name;
    private String image;

    public ColorResponse(Color color) {
        id = color.getId();
        name = color.getName();
        image = color.getImage();
    }
}
