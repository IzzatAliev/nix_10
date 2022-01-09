package ua.com.alevel.persistence.type;

import lombok.Getter;

@Getter
public enum CourseLevel {

    ENTRY_LEVEL("Entry level"),
    INTERMEDIATE_LEVEL("Intermediate level"),
    PROFESSIONAL_LEVEL("Professional level"),
    ALL_LEVELS("All levels");

    private final String level;

    CourseLevel(String level) {
        this.level = level;
    }
}
