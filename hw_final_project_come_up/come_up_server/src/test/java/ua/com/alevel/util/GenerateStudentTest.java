//package ua.com.alevel.util;
//
//import ua.com.alevel.persistence.entity.user.Student;
//import ua.com.alevel.persistence.type.RoleType;
//
//import java.util.Date;
//
//public class GenerateStudentTest {
//
//    public static final String firstName = "Kirill";
//    public static final String lastName = "Brave";
//    public static final String imageUrl = "photo.jpg";
//    public static final String password = "kirillop";
//    public static final boolean enabled = true;
//    public static final RoleType roleType = RoleType.ROLE_STUDENT;
//
//    public static Student generateRandomStudent(Long id,Date created,Date updated,Date birthDay, String email) {
//        Student student = new Student();
//        student.setId(id);
//        student.setCreated(created);
//        student.setUpdated(updated);
//        student.setBirthDay(birthDay);
//        student.setCreated(created);
//        student.setFirstName(firstName);
//        student.setLastName(lastName);
//        student.setImageUrl(imageUrl);
//        student.setEmail(email);
//        student.setPassword(password);
//        student.setEnabled(enabled);
//        student.setRoleType(roleType);
//        return student;
//    }
//}
