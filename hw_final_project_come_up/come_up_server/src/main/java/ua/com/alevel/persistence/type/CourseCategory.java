package ua.com.alevel.persistence.type;

import lombok.Getter;

@Getter
public enum CourseCategory {

    DEVELOPMENT("Development"),
    BUSINESS("Business"),
    FINANCE_AND_ACCOUNTING("Finance & Accounting"),
    IT_AND_SOFTWARE("IT & Software"),
    OFFICE_SOFTWARE("Office software"),
    PERSONAL_GROWTH("Personal growth"),
    DESIGN("Design"),
    MARKETING("Marketing"),
    LIFESTYLE("Lifestyle"),
    PHOTOGRAPHY_VIDEO("Photography & Video"),
    HEALTH_FITNESS("Health & Fitness"),
    EDUCATIONAL_AND_ACADEMIC_DISCIPLINES("Education & Academic disciplines"),
    OTHER("other");

    private final String category;

    CourseCategory(String category) {
        this.category = category;
    }
}
