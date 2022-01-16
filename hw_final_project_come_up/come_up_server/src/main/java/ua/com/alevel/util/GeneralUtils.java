package ua.com.alevel.util;

import com.javachinna.dto.LocalUser;
import com.javachinna.dto.SocialProvider;
import com.javachinna.dto.UserInfo;
import com.javachinna.model.Role;
import com.javachinna.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.com.alevel.api.dto.UserInfo;
import ua.com.alevel.persistence.type.RoleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<RoleType> roleType) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (RoleType role : roleType) {
			authorities.add(new SimpleGrantedAuthority(role.getType()));
		}
		return authorities;
	}

//	public static SocialProvider toSocialProvider(String providerId) {
//		for (SocialProvider socialProvider : SocialProvider.values()) {
//			if (socialProvider.getProviderType().equals(providerId)) {
//				return socialProvider;
//			}
//		}
//		return SocialProvider.LOCAL;
//	}

	public static UserInfo buildUserInfo(LocalUser localUser) {
		List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		User user = localUser.getUser();
		return new UserInfo(user.getId().toString(), user.getDisplayName(), user.getEmail(), roles);
	}
}
