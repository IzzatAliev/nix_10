package ua.com.alevel.facade.user;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import ua.com.alevel.api.dto.LocalUser;
import ua.com.alevel.api.dto.request.SignUpRequest;
import ua.com.alevel.exception.UserAlreadyExistAuthenticationException;
import ua.com.alevel.persistence.entity.user.Student;

import java.util.Map;
import java.util.Optional;

public interface StudentFacade {

    Student registerNewStudent(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    Student findStudentByEmail(String email);

    Optional<Student> findStudentById(Long id);

    LocalUser processStudentRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);

}
