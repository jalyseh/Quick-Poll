package com.example.QuickPoll.controller;

import com.example.QuickPoll.model.Vote;
import com.example.QuickPoll.repository.VoteRepository;
import com.example.QuickPoll.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VoteController {

        @Autowired
        private VoteService voteService;

        @RequestMapping(value="/polls/{id}/votes", method= RequestMethod.POST)
        public ResponseEntity<?> createVote(@PathVariable Long id, @RequestBody Vote vote) {

        vote = voteService.createVote(vote);

        // Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder. fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
}
        @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
        public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
                return voteService.findPollByID(pollId);
        }

}
