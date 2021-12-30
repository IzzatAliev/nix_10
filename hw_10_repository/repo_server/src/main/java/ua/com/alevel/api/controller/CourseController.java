package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.CourseRequestDto;
import ua.com.alevel.api.dto.response.CourseResponseDto;
import ua.com.alevel.api.dto.response.DataContainer;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.facade.StudentFacade;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseFacade courseFacade;
    private final StudentFacade studentFacade;

    public CourseController(CourseFacade courseFacade, StudentFacade studentFacade) {
        this.courseFacade = courseFacade;
        this.studentFacade = studentFacade;
    }

    @PostMapping
    public ResponseEntity<DataContainer<Boolean>> create(@RequestBody CourseRequestDto dto) {
        courseFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> update(@RequestBody CourseRequestDto dto, @PathVariable Long id) {
        courseFacade.update(dto, id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> delete(@PathVariable Long id) {
        courseFacade.delete(id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<CourseResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new DataContainer<>(courseFacade.findById(id)));
    }

    @GetMapping
    public ResponseEntity<PageData<CourseResponseDto>> findAll(@RequestParam(required = false) Long studentId, WebRequest webRequest) {
        if (studentId != null){
            ResponseEntity.ok(studentFacade.findById(studentId)); //TODO later
        }
        return ResponseEntity.ok(courseFacade.findAll(webRequest));
    }
}
