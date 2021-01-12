package com.palmerale91.sportsbetting.model.controllers;

import com.palmerale91.sportsbetting.model.SportEvent;
import com.palmerale91.sportsbetting.model.respositories.SportEventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sportevents")
public class SportEventController {
    private final SportEventRepository repository;

    public SportEventController(SportEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SportEvent> getData() {
        return repository.getData();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public SportEvent get(@PathVariable Integer id) {
        return repository.getData().stream()
                .filter(x -> x.getId() == id)
                .findFirst() // returns Optional<SportEvent>
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found")
                );
    }

    @PostMapping
    public ResponseEntity<SportEvent> create(@RequestBody final SportEvent event) {
        Integer maxId = repository.getData().stream()
                .map(SportEvent::getId)
                .max(Comparator.comparingInt(x -> x))
                .orElse(0);

        event.setId(++maxId);
        event.setDate(ZonedDateTime.now());
        repository.getData().add(event);

        return new ResponseEntity(event, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id) {
        boolean removed = repository.getData().removeIf(x -> x.getId() == id);
        if(!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public SportEvent update(@PathVariable Integer id, @RequestBody SportEvent event) {
        boolean removed = repository.getData().removeIf(x -> x.getId() == id);
        if(!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        event.setId(id);
        repository.getData().add(event);
        return event;
    }
}
