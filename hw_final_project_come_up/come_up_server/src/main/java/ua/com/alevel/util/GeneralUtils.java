package ua.com.alevel.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.com.alevel.api.dto.LocalUser;
import ua.com.alevel.api.dto.UserInfo;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.RoleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final RoleType roleTypes) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.getType()));
		authorities.add(new SimpleGrantedAuthority(RoleType.ROLE_TEACHER.getType()));
		authorities.add(new SimpleGrantedAuthority(RoleType.ROLE_STUDENT.getType()));
		return authorities;
	}

	public static UserInfo buildUserInfo(LocalUser localUser) {
		List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		User user = localUser.getUser();
		return new UserInfo(user.getId().toString(), user.getFullName(), user.getEmail(), user.getRoleType());
	}
}
