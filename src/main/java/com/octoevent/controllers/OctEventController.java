package com.octoevent.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.octoevent.models.Commit;
import com.octoevent.repositories.CommitEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
public class OctEventController {

    @Autowired
    private CommitEventRepository repository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/commits/{numberIssue}/events")
    public ResponseEntity<?> getCommitByNumber(@PathVariable Long numberIssue) {
        log.info("OctoEventController - getCommitByNumber...");
        Optional<Commit> issue = repository.findById(numberIssue);
        if (issue.isPresent()) {
            log.info("OctoEventController - getCommitByNumber - commit: {}", issue);
            return ResponseEntity.ok(issue.get());
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/commits")
    public void saveCommit(@RequestBody JsonNode body) throws JsonParseException, JsonMappingException, IOException {
        log.info("OctEventController - saveCommit...");
        log.info("OctEventController - saveCommit - Commit: {}", body.get("commits").get(0));
        ObjectMapper mapper = new ObjectMapper();
        Commit issueEvent = mapper.readValue(body.get("commits").get(0).toString(), Commit.class);
        repository.save(issueEvent);
    }

}
