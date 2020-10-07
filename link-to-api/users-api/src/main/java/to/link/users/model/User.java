package to.link.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import to.link.users.model.util.UserData;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static to.link.users.constant.UserMessages.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserData userData;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JsonIgnore
    private Credential credential;

    @Column(length = 100, nullable = false)
    @Email(message = INVALID_EMAIL)
    @NotBlank(message = EMAIL_MUST_NOT_BE_EMPTY)
    private String email;

}
