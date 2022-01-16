package ua.com.alevel.persistence.type;

import lombok.Getter;

@Getter
public enum RoleType {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_TEACHER("ROLE_TEACHER");

    private final String type;

    RoleType(String type){
        this.type = type;
    }
}
