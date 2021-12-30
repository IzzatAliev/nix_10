package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SortData {

    private String sort;
    private String order;

    public SortData() { }

    public SortData(String sort, String order) {
        this.sort = sort;
        this.order = order;
    }
}
