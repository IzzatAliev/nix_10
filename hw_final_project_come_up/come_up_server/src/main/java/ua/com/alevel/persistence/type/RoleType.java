package ua.com.alevel.persistence.type;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum RoleType {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_TEACHER("ROLE_TEACHER");

    private final String type;

    RoleType(String type){
        this.type = type;
    }

    public static Stream<RoleType> stream() {
        return Stream.of(RoleType.values());
    }
}
