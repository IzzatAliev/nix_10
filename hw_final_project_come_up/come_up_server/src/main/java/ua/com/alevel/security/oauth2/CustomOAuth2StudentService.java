package ua.com.alevel.security.oauth2;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.exception.OAuth2AuthenticationProcessingException;
import ua.com.alevel.facade.user.StudentFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomOAuth2StudentService extends DefaultOAuth2UserService {

	private final StudentFacade studentFacade;
	private final Environment env;

	public CustomOAuth2StudentService(StudentFacade studentFacade, Environment env) {
		this.studentFacade = studentFacade;
		this.env = env;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		try {
			Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
			String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();
//			if (provider.equals(SocialProvider.LINKEDIN.getProviderType())) {
//				populateEmailAddressFromLinkedIn(oAuth2UserRequest, attributes);
//			}
			return studentFacade.processStudentRegistration(provider, attributes, null, null);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Throwing an instance of AuthenticationException will trigger the
			// OAuth2AuthenticationFailureHandler
			throw new OAuth2AuthenticationProcessingException(ex.getMessage(), ex.getCause());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populateEmailAddressFromLinkedIn(OAuth2UserRequest oAuth2UserRequest, Map<String, Object> attributes) throws OAuth2AuthenticationException {
		String emailEndpointUri = env.getProperty("linkedin.email-address-uri");
		Assert.notNull(emailEndpointUri, "LinkedIn email address end point required");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + oAuth2UserRequest.getAccessToken().getTokenValue());
		HttpEntity<?> entity = new HttpEntity<>("", headers);
		ResponseEntity<Map> response = restTemplate.exchange(emailEndpointUri, HttpMethod.GET, entity, Map.class);
		List<?> list = (List<?>) response.getBody().get("elements");
		Map map = (Map<?, ?>) ((Map<?, ?>) list.get(0)).get("handle~");
		attributes.putAll(map);
	}
}
