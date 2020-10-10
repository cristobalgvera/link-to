package to.link.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import to.link.urlshortener.constant.UrlMessages;
import to.link.users.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    @Size(max = 5, message = UrlMessages.INVALID_SHORT_URI_SIZE)
    @Pattern(regexp = "^[\\w_-]+$", message = UrlMessages.INVALID_SHORT_URI_PATTERN)
    private String shortUri;

//    @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
//            message = INVALID_URI)
    @NotBlank(message = UrlMessages.NO_URI)
    @URL(protocol = "https", message = UrlMessages.INVALID_URI)
    @Column(nullable = false)
    private String uri;

    @ElementCollection
    @OrderBy
    private List<LocalDateTime> clickLogs = new ArrayList<>();

    public void addClickLog(LocalDateTime clickLog) {
        clickLogs.add(clickLog);
    }
}
