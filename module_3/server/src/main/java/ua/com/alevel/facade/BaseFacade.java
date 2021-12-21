package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.RequestDto;
import ua.com.alevel.api.dto.response.ResponseDto;

import java.util.List;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ request);
    void update(REQ request, Long id);
    void delete(Long id);
    RES findById(Long id);
    List<RES> findAll();
}
