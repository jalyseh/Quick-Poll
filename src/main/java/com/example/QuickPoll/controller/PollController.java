package com.example.QuickPoll.controller;

import com.example.QuickPoll.exception.ResourceNotFoundException;
import com.example.QuickPoll.model.Poll;
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

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollService.getPollById(pollId);
        if(poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

    //GET ALL POLLS
    @RequestMapping("/polls")
    public Iterable<Poll> getAllPosts(){
        return pollService.findAllPosts();
    }

    //GET POLL BY ID
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {

        verifyPoll(pollId);
        Optional<Poll> p = pollService.getPollById(pollId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/polls/{id}")
//    public Optional<Poll> getPoll(@PathVariable Long id){
//
//        verifyPoll(id);
//        Optional<Poll> p = pollService.findOne(id);
//
//        if(p.isEmpty()){
//            throw new ResourceNotFoundException("Poll with id " + id + " not found");
//        }
//        return pollService.findOne(id);
//    }
    //Notice that an @ResponseStatus annotation is declared at the class level.
    // The annotation instructs Spring MVC that an HttpStatus NOT_FOUND (404 code) should be used in the response when a ResourceNotFoundException is thrown.

    //CREATE A POLL
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

    //DELETE POLL
    @RequestMapping(value="/polls/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long id) {

       verifyPoll(id);
        pollService.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //UPDATE POLL
    @RequestMapping(value="/polls/{id}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long id) {

        verifyPoll(id);
        Poll p = pollService.updatePoll(id, poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

