package roman.pidkostelnyi.victoriaarmario.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderSearchRequest {
    private String value;
    private Long minSum;
    private Long maxSum;
    private Date dateFrom;
    private Date dateTo;

    private PaginationRequest pagination;
}
