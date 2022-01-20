package ua.com.alevel.api.dto.request;

import lombok.Data;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.validator.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignUpRequest {

    private Long userID;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

//    @NotEmpty
//    private RoleType roleType;

    @NotEmpty
    private String email;

    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;

    @NotEmpty
    private String matchingPassword;

    public SignUpRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.roleType = roleType;
        this.email = email;
        this.password = password;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public Builder addFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder addLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

//        public Builder addRoleType(final RoleType roleType) {
//            this.roleType = roleType;
//            return this;
//        }

        public Builder addEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder addPassword(final String password) {
            this.password = password;
            return this;
        }

        public SignUpRequest build() {
            return new SignUpRequest(firstName, lastName, email, password);
        }
    }
}
