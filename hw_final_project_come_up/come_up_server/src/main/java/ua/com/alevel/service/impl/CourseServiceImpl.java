package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.course.Course;
import ua.com.alevel.persistence.repository.course.CourseRepository;
import ua.com.alevel.service.CourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CrudRepositoryHelper<Course, CourseRepository> crudRepositoryHelper;

    public CourseServiceImpl(CourseRepository courseRepository, CrudRepositoryHelper<Course, CourseRepository> crudRepositoryHelper) {
        this.courseRepository = courseRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Course entity) {
        crudRepositoryHelper.create(courseRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Course entity) {
        crudRepositoryHelper.update(courseRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(courseRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return crudRepositoryHelper.findById(courseRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(courseRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findByTeacherId(Long teacherId) {
        if (teacherId != null){
            return courseRepository.findByTeacherId(teacherId);
        }
        throw new EntityNotFoundException("Entity not found");
    }
}
