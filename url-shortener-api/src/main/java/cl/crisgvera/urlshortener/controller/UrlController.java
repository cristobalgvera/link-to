package cl.crisgvera.urlshortener.controller;

import cl.crisgvera.urlshortener.model.Url;
import cl.crisgvera.urlshortener.service.util.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UrlController {
    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> redirect(@PathVariable String id) {
        return new ResponseEntity<>(urlService.redirect(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Url> shorten(@Valid @RequestBody Url url) {
        return new ResponseEntity<>(urlService.save(url), HttpStatus.CREATED);
    }

}
