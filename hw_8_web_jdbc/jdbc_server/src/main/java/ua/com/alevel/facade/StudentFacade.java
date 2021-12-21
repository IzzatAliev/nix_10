package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.StudentRequestDto;
import ua.com.alevel.api.dto.response.StudentResponseDto;

import java.util.List;
import java.util.Map;

public interface StudentFacade extends BaseFacade<StudentRequestDto, StudentResponseDto> {

    Map<Long, String> findByCourseId(Long courseId);
    List<StudentResponseDto> findAllByCourseId(Long courseId);
}
