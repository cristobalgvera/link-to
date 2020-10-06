package cl.crisgvera.urlshortener.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cl.crisgvera.urlshortener.constant.UrlMessages.*;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    @Size(max = 5, message = INVALID_SHORT_URI_SIZE)
    @Pattern(regexp = "^[\\w_-]+$", message = INVALID_SHORT_URI_PATTERN)
    private String shortUri;

//    @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
//            message = INVALID_URI)
    @NotBlank(message = NO_URI)
    @URL(protocol = "https", message = INVALID_URI)
    @Column(nullable = false)
    private String uri;

    @ElementCollection
    @OrderBy
    private List<LocalDateTime> clickLogs = new ArrayList<>();

    public Url() {
    }

    public Long getId() {
        return this.id;
    }

    public @Size(max = 5, message = INVALID_SHORT_URI_SIZE) @Pattern(regexp = "^[\\w_-]+$", message = INVALID_SHORT_URI_PATTERN) String getShortUri() {
        return this.shortUri;
    }

    public @NotBlank(message = NO_URI) @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
            message = INVALID_URI) String getUri() {
        return this.uri;
    }

    public List<LocalDateTime> getClickLogs() {
        return this.clickLogs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShortUri(@Size(max = 5, message = INVALID_SHORT_URI_SIZE) @Pattern(regexp = "^[\\w_-]+$", message = INVALID_SHORT_URI_PATTERN) String shortUri) {
        this.shortUri = shortUri;
    }

    public void setUri(@NotBlank(message = NO_URI) @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
            message = INVALID_URI) String uri) {
        this.uri = uri;
    }

    public void setClickLogs(List<LocalDateTime> clickLogs) {
        this.clickLogs = clickLogs;
    }

    public void addClickLog(LocalDateTime clickLog) {
        clickLogs.add(clickLog);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Url)) return false;
        final Url other = (Url) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        final Object this$shortUri = this.getShortUri();
        final Object other$shortUri = other.getShortUri();
        if (!Objects.equals(this$shortUri, other$shortUri)) return false;
        final Object this$uri = this.getUri();
        final Object other$uri = other.getUri();
        if (!Objects.equals(this$uri, other$uri)) return false;
        final Object this$clickLogs = this.getClickLogs();
        final Object other$clickLogs = other.getClickLogs();
        return Objects.equals(this$clickLogs, other$clickLogs);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Url;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $shortUri = this.getShortUri();
        result = result * PRIME + ($shortUri == null ? 43 : $shortUri.hashCode());
        final Object $uri = this.getUri();
        result = result * PRIME + ($uri == null ? 43 : $uri.hashCode());
        final Object $clickLogs = this.getClickLogs();
        result = result * PRIME + ($clickLogs == null ? 43 : $clickLogs.hashCode());
        return result;
    }

    public String toString() {
        return "Url(id=" + this.getId() + ", shortUri=" + this.getShortUri() + ", uri=" + this.getUri() + ", clickLogs=" + this.getClickLogs() + ")";
    }
}
