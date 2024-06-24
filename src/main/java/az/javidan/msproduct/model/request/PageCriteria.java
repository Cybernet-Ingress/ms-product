package az.javidan.msproduct.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
public class PageCriteria {
    private Integer page = 0;
    private Integer count = 5;
    private String sortBy = "id";
    private String sortDirection = "desc";
}
