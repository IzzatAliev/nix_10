package ua.com.alevel.api.dto;

import lombok.Value;
import ua.com.alevel.persistence.type.RoleType;

@Value
public class UserInfo {

    private String id, displayName, email;
    private RoleType roleType;
}
