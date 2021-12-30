package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.repository.CourseRepository;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CrudRepositoryHelper<Course, CourseRepository> crudRepositoryHelper;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CrudRepositoryHelper<Course, CourseRepository> crudRepositoryHelper, CourseRepository courseRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.courseRepository = courseRepository;
    }

    @Override
    public void create(Course entity) {
        crudRepositoryHelper.create(courseRepository, entity);
    }

    @Override
    public void update(Course entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Course> findById(Long id) {
        return crudRepositoryHelper.findById(courseRepository, id);
    }

    @Override
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        DataTableResponse<Course> dataTableResponse = crudRepositoryHelper.findAll(courseRepository, request);
        long count = courseRepository.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findByStudentId(Long studentId) {
        return courseRepository.findByStudentId(studentId);
    }

    @Override
    public List<Course> findAllByStudentId(Long studentId) {
        return courseRepository.findAllByStudentId(studentId);
    }
}
