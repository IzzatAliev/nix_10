package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto extends RequestDto {

    private String name;
    private boolean income;
}
