package com.example.QuickPoll.controller;

import com.example.QuickPoll.dto.VoteResult;
import com.example.QuickPoll.model.Vote;
import com.example.QuickPoll.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComputeResultController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value="/computeresult", method= RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {

        VoteResult voteResult = new VoteResult();

        Iterable<Vote> allVotes = voteService.findPollByID(pollId); // Algorithm to count votes
        voteResult = voteService.computeResult(pollId);
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
