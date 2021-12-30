package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.CourseRequestDto;
import ua.com.alevel.api.dto.response.CourseResponseDto;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseFacadeImpl implements CourseFacade {

    private final CourseService courseService;
    private final StudentService studentService;

    public CourseFacadeImpl(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void create(CourseRequestDto courseRequestDto) {
        Course course = new Course();
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCredit(courseRequestDto.getCredit());
        course.setCourseType(courseRequestDto.getCourseType());
        course.setDescription(courseRequestDto.getDescription());
        courseService.create(course);
    }

    @Override
    public void update(CourseRequestDto courseRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CourseResponseDto findById(Long id) {
        Optional<Course> courseOptional = courseService.findById(id);
        return courseOptional.map(CourseResponseDto::new).orElse(null);
    }

    @Override
    public PageData<CourseResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Course> tableResponse = courseService.findAll(dataTableRequest);

        List<CourseResponseDto> courses = tableResponse.getItems().stream().
                map(CourseResponseDto::new).
                peek(courseResponseDto -> courseResponseDto.setStudentCount((Integer) tableResponse.
                        getOtherParamMap().get(courseResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<CourseResponseDto> pageData = (PageData<CourseResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(courses);

        return pageData;
    }

    @Override
    public List<CourseResponseDto> findAllByStudentId(Long studentId) {
        return courseService.findAllByStudentId(convertToLong(studentService.findById(studentId))).stream()
                .map(CourseResponseDto::new).collect(Collectors.toList());
    }

    public static Long convertToLong(Object o) {
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }

    @Override
    public Map<Long, String> findByStudentId(Long studentId) {
        return courseService.findByStudentId(studentId);
    }
}
