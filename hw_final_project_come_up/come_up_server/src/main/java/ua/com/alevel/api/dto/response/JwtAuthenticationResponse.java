package ua.com.alevel.api.dto.response;

import lombok.Value;
import ua.com.alevel.api.dto.UserInfo;

@Value
public class JwtAuthenticationResponse {

	private String accessToken;
	private UserInfo user;
}
