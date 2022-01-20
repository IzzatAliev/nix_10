package ua.com.alevel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.type.RoleType;

import java.util.Calendar;
import java.util.Date;

@Lazy
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}
		// Create initial roles
		RoleType studentRole = RoleType.ROLE_STUDENT;
		RoleType adminRole = RoleType.ROLE_ADMIN;
		RoleType teacherRole = RoleType.ROLE_TEACHER;
		createAdminIfNotFound("admin@gmail.com", adminRole);
		alreadySetup = true;
	}

	@Transactional
	private final Admin createAdminIfNotFound(final String email, RoleType role) {
		Admin admin = adminRepository.findByEmail(email);
		if (admin == null) {
			admin = new Admin();
			admin.setFirstName("Admin");
			admin.setLastName("Admin");
			admin.setEmail(email);
			admin.setPassword(passwordEncoder.encode("admin@"));
			admin.setRoleType(role);
			admin.setEnabled(true);
			Date now = Calendar.getInstance().getTime();
			admin.setCreated(now);
			admin.setUpdated(now);
			admin = adminRepository.save(admin);
		}
		return admin;
	}
}
