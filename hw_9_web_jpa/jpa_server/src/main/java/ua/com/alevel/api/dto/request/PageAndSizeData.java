package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageAndSizeData {

    int page;
    int size;

    public PageAndSizeData() { }

    public PageAndSizeData(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
