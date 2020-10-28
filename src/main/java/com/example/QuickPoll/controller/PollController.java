package com.example.QuickPoll.controller;

import com.example.QuickPoll.model.Poll;
import com.example.QuickPoll.repository.PollRepository;
import com.example.QuickPoll.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping("/polls")
    public Iterable<Poll> getAllPosts(){
        return pollService.findAllPosts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/polls/{id}")
    public Optional<Poll> getPoll(@PathVariable Long id){
        return pollService.findOne(id);
    }

    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){

        pollService.createPoll(poll);

    // Set the location header for the newly created resource
    HttpHeaders responseHeaders = new HttpHeaders();
    URI newPollUri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(poll.getId())
            .toUri();

    responseHeaders.setLocation(newPollUri);
    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long id) {
        pollService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{id}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long id) {

        Poll p = pollService.updatePoll(id, poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

