package az.javidan.msproduct.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeUpdateRequestDto {

    private Long id;

    private Boolean subscribe;
}
