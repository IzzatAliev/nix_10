package ua.com.alevel.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ua.com.alevel.persistence.entity.Category;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDto extends ResponseDto {

    private String name;
    private BigDecimal price;
    private boolean income;

    public CategoryResponseDto(Category category) {
        BeanUtils.copyProperties(category, this);
    }
}

