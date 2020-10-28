package com.example.QuickPoll.service;

import com.example.QuickPoll.dto.OptionCount;
import com.example.QuickPoll.dto.VoteResult;
import com.example.QuickPoll.model.Vote;
import com.example.QuickPoll.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    public Vote createVote(Vote vote){
       return voteRepository.save(vote);
    }

    public Iterable<Vote> findPollByID(Long pollID){
        return voteRepository.findByPoll(pollID);
    }

    public VoteResult computeResult(Long pollId) {

        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        int totalVotes = 0;

        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for (Vote v : allVotes) {
            totalVotes++;
            // Get the OptionCount corresponding to this Option
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
            voteResult.setTotalVotes(totalVotes);
            voteResult.setResults(tempMap.values());
            return voteResult;
    }
}
