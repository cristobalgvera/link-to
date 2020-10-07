package to.link.users.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static to.link.users.constant.CredentialMessages.*;

@Entity
@Data
public class Credential {

    @Id
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    private User user;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank(message = USERNAME_MUST_NOT_BE_EMPTY)
    @Size(max = 50, message = USERNAME_TOO_LONG)
    @Pattern(regexp = "^[\\w_-]+$", message = INVALID_USERNAME_PATTERN)
    private String username;

    @Column(length = 150, nullable = false)
    @NotBlank(message = PASSWORD_MUST_NOT_BE_EMPTY)
    @Size(min = 8, message = PASSWORD_TOO_SHORT)
    private String password;

}
