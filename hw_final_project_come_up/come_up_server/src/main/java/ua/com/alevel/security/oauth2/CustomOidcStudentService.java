package ua.com.alevel.security.oauth2;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.OAuth2AuthenticationProcessingException;
import ua.com.alevel.facade.user.StudentFacade;

@Service
public class CustomOidcStudentService extends OidcUserService {

	private final StudentFacade studentFacade;

	public CustomOidcStudentService(StudentFacade studentFacade) {
		this.studentFacade = studentFacade;
	}

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(userRequest);
		try {
			return studentFacade.processStudentRegistration(userRequest.getClientRegistration().getRegistrationId(), oidcUser.getAttributes(), oidcUser.getIdToken(),
					oidcUser.getUserInfo());
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Throwing an instance of AuthenticationException will trigger the
			// OAuth2AuthenticationFailureHandler
			throw new OAuth2AuthenticationProcessingException(ex.getMessage(), ex.getCause());
		}
	}
}
