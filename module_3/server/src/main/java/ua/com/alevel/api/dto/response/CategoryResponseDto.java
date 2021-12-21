package ua.com.alevel.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ua.com.alevel.persistence.entity.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto extends ResponseDto {

    private String name;
    private boolean income;

    public CategoryResponseDto(Category category) {
        BeanUtils.copyProperties(category, this);
    }
}

