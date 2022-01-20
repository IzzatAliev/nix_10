package ua.com.alevel.api.dto;

import lombok.Value;
import ua.com.alevel.persistence.type.RoleType;

@Value
public class UserInfo {

    private String id;
    private String fullName;
    private String email;
    private RoleType roleType;
}
