package ua.com.alevel.facade.user.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.api.dto.LocalUser;
import ua.com.alevel.exception.ResourceNotFoundException;
import ua.com.alevel.facade.user.StudentFacade;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.util.GeneralUtils;

@Service("localUserDetailService")
public class LocalUserDetailService implements UserDetailsService {

	private final StudentFacade studentFacade;

	public LocalUserDetailService(StudentFacade studentFacade) {
		this.studentFacade = studentFacade;
	}

	@Override
	@Transactional
	public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
		Student student = studentFacade.findStudentByEmail(email);
		if (student == null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}
		return createLocalStudent(student);
	}

	@Transactional
	public LocalUser loadUserById(Long id) {
		Student student = studentFacade.findStudentById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return createLocalStudent(student);
	}

	private LocalUser createLocalStudent(Student student) {
		return new LocalUser(student.getEmail(), student.getPassword(), student.getEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(student.getRoleType()), student);
	}
}
