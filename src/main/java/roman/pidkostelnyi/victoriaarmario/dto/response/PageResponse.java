package roman.pidkostelnyi.victoriaarmario.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private Integer totalPages;
    private Long totalElements;

    public PageResponse(List<T> content, Integer totalPages, Long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
