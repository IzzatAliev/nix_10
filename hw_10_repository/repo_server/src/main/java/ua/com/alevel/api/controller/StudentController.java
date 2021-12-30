package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.StudentRequestDto;
import ua.com.alevel.api.dto.response.DataContainer;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.api.dto.response.StudentResponseDto;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.facade.StudentFacade;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentFacade studentFacade;
    private final CourseFacade courseFacade;

    public StudentController(StudentFacade studentFacade, CourseFacade courseFacade) {
        this.studentFacade = studentFacade;
        this.courseFacade = courseFacade;
    }

    @PostMapping
    public ResponseEntity<DataContainer<Boolean>> create(@RequestBody StudentRequestDto dto) {
        studentFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> update(@RequestBody StudentRequestDto dto, @PathVariable Long id) {
        studentFacade.update(dto, id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<StudentResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new DataContainer<>(studentFacade.findById(id)));
    }

    @GetMapping
    public ResponseEntity<PageData<StudentResponseDto>> findAll(@RequestParam(required = false) Long courseId, WebRequest webRequest) {
        if (courseId != null){
            ResponseEntity.ok(studentFacade.findByCourseId(courseId));//TODO later
        }
        return ResponseEntity.ok(studentFacade.findAll(webRequest));
    }
}
