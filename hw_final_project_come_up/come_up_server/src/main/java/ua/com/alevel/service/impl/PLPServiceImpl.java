package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.course.Course;
import ua.com.alevel.persistence.entity.user.Teacher;
import ua.com.alevel.persistence.repository.course.CourseRepository;
import ua.com.alevel.persistence.repository.user.TeacherRepository;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PLPServiceImpl implements PLPService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CrudRepositoryHelper<Teacher, TeacherRepository> crudRepositoryHelper;

    public PLPServiceImpl(CourseRepository courseRepository,
                          TeacherRepository teacherRepository,
                          CrudRepositoryHelper<Teacher, TeacherRepository> crudRepositoryHelper) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public List<Course> search(Map<String, Object> queryMap) {
        if (queryMap.get(WebUtil.TEACHER_PARAM) != null){
            Long teacherId = Long.parseLong(String.valueOf(queryMap.get(WebUtil.TEACHER_PARAM)));
            Optional<Teacher> teacher = crudRepositoryHelper.findById(teacherRepository,teacherId);
            return courseRepository.findByTeacher(teacher.get());
        }
        return courseRepository.findAll();
    }
}
