package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.search.SearchCourseInfo;
import ua.com.alevel.persistence.entity.user.Teacher;
import ua.com.alevel.persistence.repository.search.SearchCourseInfoRepository;
import ua.com.alevel.persistence.repository.user.TeacherRepository;
import ua.com.alevel.service.SearchCourseInfoService;
import ua.com.alevel.util.WebUtil;

import java.util.Optional;

@Service
public class SearchCourseInfoServiceImpl implements SearchCourseInfoService {

    private final TeacherRepository teacherRepository;
    private final SearchCourseInfoRepository searchCourseInfoRepository;

    public SearchCourseInfoServiceImpl(TeacherRepository teacherRepository,
                                       SearchCourseInfoRepository searchCourseInfoRepository) {
        this.teacherRepository = teacherRepository;
        this.searchCourseInfoRepository = searchCourseInfoRepository;
    }

    @Override
    public void process(String searchParam, Long id) {
        if(searchParam.equals(WebUtil.TEACHER_PARAM)){
            Optional<Teacher> teacher = teacherRepository.findById(id);
            if (teacher.isPresent()){
                SearchCourseInfo searchCourseInfo = searchCourseInfoRepository.findByTeacher(teacher.get().getFullName());
                if (searchCourseInfo == null){
                    searchCourseInfo = new SearchCourseInfo();
                    searchCourseInfo.setTeacher(teacher.get().getFullName());
                    searchCourseInfo.setCountTeacher(1L);
                } else {
                    Long count = searchCourseInfo.getCountTeacher();
                    ++count;
                    searchCourseInfo.setCountTeacher(count);
                }
                searchCourseInfoRepository.save(searchCourseInfo);
            }
        }
    }
}
