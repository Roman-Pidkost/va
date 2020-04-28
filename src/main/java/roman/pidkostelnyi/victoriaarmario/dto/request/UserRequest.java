package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
