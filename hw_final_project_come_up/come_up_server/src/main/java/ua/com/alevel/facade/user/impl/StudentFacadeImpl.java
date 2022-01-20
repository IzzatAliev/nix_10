package ua.com.alevel.facade.user.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import ua.com.alevel.api.dto.LocalUser;
import ua.com.alevel.api.dto.request.SignUpRequest;
import ua.com.alevel.exception.UserAlreadyExistAuthenticationException;
import ua.com.alevel.facade.user.StudentFacade;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.user.StudentService;

import java.util.*;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    public StudentFacadeImpl(StudentService studentService, PasswordEncoder passwordEncoder) {
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student registerNewStudent(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && studentService.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
        } else if (studentService.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        Student student = buildStudent(signUpRequest);
        Date now = Calendar.getInstance().getTime();
        student.setCreated(now);
        student.setUpdated(now);
        studentService.create(student);
        studentService.flush();
        return student;
    }

    private Student buildStudent(final SignUpRequest formDTO) {
        Student student = new Student();
        student.setFirstName(formDTO.getFirstName());
        student.setLastName(formDTO.getLastName());
        student.setEmail(formDTO.getEmail());
        student.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        student.setRoleType(RoleType.ROLE_STUDENT);
        student.setEnabled(true);
        return student;
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentService.findByEmail(email);
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return Optional.empty();
    }

    @Override
    public LocalUser processStudentRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        return null;
    }
}
