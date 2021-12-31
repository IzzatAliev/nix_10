package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CategoryRequestDto extends RequestDto {

    private String name;
    private BigDecimal price;
    private boolean income;
}
