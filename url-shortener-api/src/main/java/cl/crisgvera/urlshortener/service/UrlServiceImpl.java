package cl.crisgvera.urlshortener.service;

import cl.crisgvera.urlshortener.dto.ClickLog;
import cl.crisgvera.urlshortener.model.Url;
import cl.crisgvera.urlshortener.repository.UrlRepository;
import cl.crisgvera.urlshortener.service.util.UrlService;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import static cl.crisgvera.urlshortener.constant.UrlServiceMessages.*;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url findById(String shortUri) {
        return urlRepository.findByShortUri(shortUri)
                .orElseThrow(() -> new NoSuchElementException(URL_WITH_SHORT_URL + shortUri + DOES_NOT_EXIST));
    }

    @Override
    public Collection<Url> findAll() {
        return urlRepository.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public Url save(Url url) {
        return urlRepository.save(checkShortUri(url, true));
    }

    @Override
    public Url update(Url url) {
        return urlRepository.save(checkShortUri(url, false));
    }

    @Override
    public ClickLog getClickLogs(String shortUri) {
        ClickLog clickLog = new ClickLog();
        clickLog.setClickLogs(findById(shortUri).getClickLogs());

        return clickLog;
    }

    @Override
    public void deleteById(String shortUri) {
        findById(shortUri);
        urlRepository.deleteByShortUri(shortUri);
    }

    @Override
    public String redirect(String shortUri) {
        Url url = findById(shortUri);
        trackClicks(url);
        return url.getUri();
    }

    private void trackClicks(Url url) {
        url.addClickLog(LocalDateTime.now());
        update(url);
    }

    private Url checkShortUri(Url url, boolean toSave) {
        if (url.getShortUri() == null) {
            url.setShortUri(NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 5));

            while (true) try {
                checkExistence(url, toSave);
                return url;
            } catch (Exception e) {
                url.setShortUri(NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 5));
            }
        }

        checkExistence(url, toSave);
        return url;
    }

    private void checkExistence(Url url, boolean toSave) throws RuntimeException {
        boolean exist = false;

        if (url.getShortUri() != null) exist = urlRepository.existsByShortUri(url.getShortUri());

        assert !exist || !toSave : URL_WITH_SHORT_URL + url + ALREADY_EXIST;
        assert exist || toSave || url.getId() == null : URL_WITH_SHORT_URL + url + DOES_NOT_EXIST;
    }
}
