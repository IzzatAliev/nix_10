package ua.com.alevel.service.user.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Teacher;
import ua.com.alevel.persistence.repository.user.TeacherRepository;
import ua.com.alevel.service.user.TeacherService;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final CrudRepositoryHelper<Teacher, TeacherRepository> crudRepositoryHelper;

    public TeacherServiceImpl(PasswordEncoder passwordEncoder, TeacherRepository teacherRepository, CrudRepositoryHelper<Teacher, TeacherRepository> crudRepositoryHelper) {
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Teacher entity) {
        if (teacherRepository.existsByEmail(entity.getEmail())) {
            throw new EntityExistException("this teacher is exist");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.create(teacherRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Teacher entity) {
        crudRepositoryHelper.update(teacherRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(teacherRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Teacher> findById(Long id) {
        return crudRepositoryHelper.findById(teacherRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Teacher> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(teacherRepository, request);
    }

    @Override
    public void flush() {
        teacherRepository.flush();
    }

    @Override
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return teacherRepository.existsByEmail(email);
    }
}
