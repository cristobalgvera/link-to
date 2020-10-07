package to.link.users.model.util;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
public class UserData {

    @Column(length = 50)
    private String name, lastName;

    @Column(length = 15)
    private String gender;
    private LocalDate birthday;
}
