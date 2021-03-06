package to.link.urlshortener.repository;

import to.link.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUri(String shortUri);

    boolean existsByShortUri(String shortUri);

    void deleteByShortUri(String shortUri);
}
