package ua.com.alevel.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author Chinna
 *
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

	public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}
