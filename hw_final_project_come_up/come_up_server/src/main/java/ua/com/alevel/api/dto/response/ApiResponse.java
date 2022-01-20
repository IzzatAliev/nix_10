package ua.com.alevel.api.dto.response;

import lombok.Value;

@Value
public class ApiResponse {

	private Boolean success;
	private String message;
}
