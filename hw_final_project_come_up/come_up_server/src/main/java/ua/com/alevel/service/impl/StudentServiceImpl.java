package ua.com.alevel.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.repository.user.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper;

    public StudentServiceImpl(PasswordEncoder passwordEncoder, StudentRepository studentRepository, CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Student entity) {
        if (studentRepository.existsByEmail(entity.getEmail())) {
            throw new EntityExistException("this student is exist");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.create(studentRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Student entity) {
        crudRepositoryHelper.update(studentRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(studentRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return crudRepositoryHelper.findById(studentRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(studentRepository, request);
    }
}
