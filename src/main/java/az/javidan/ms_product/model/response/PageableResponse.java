package az.javidan.ms_product.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse<T> {
    private List<T> data;
    private int lastPageNumber;
    private long totalElements;
    private boolean hasNextPage;
}
