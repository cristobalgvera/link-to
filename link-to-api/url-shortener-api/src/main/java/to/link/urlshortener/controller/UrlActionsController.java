package to.link.urlshortener.controller;

import to.link.urlshortener.dto.ClickLog;
import to.link.urlshortener.model.Url;
import to.link.urlshortener.service.util.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/actions")
public class UrlActionsController {
    private final UrlService urlService;

    @Autowired
    public UrlActionsController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Url> findById(@PathVariable String id) {
        return new ResponseEntity<>(urlService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<Url>> findAll() {
        return new ResponseEntity<>(urlService.findAll(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{id}/modify")
    public ResponseEntity<Url> update(@PathVariable String id,
                                      @Valid @RequestBody Url url) {
        Url dbUrl = getOne(id);
        url.setId(dbUrl.getId());
        url.setClickLogs(dbUrl.getClickLogs());

        return new ResponseEntity<>(urlService.update(url), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        urlService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/click-log")
    public ResponseEntity<ClickLog> getClickLog(@PathVariable String id) {
        return new ResponseEntity<>(urlService.getClickLogs(id), HttpStatus.OK);
    }

    private Url getOne(String id) {
        Url url = findById(id).getBody();
        assert url != null;

        return url;
    }
}
