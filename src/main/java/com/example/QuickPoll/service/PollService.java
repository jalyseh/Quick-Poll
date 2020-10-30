package com.example.QuickPoll.service;

import com.example.QuickPoll.exception.ResourceNotFoundException;
import com.example.QuickPoll.model.Poll;
import com.example.QuickPoll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public Iterable<Poll> findAllPosts(){
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id){
        return pollRepository.findById(id);
    }

    public void createPoll(Poll poll){
       pollRepository.save(poll);
    }

    public void deleteOne(Long id){
        pollRepository.deleteById(id);
    }
    public Poll updatePoll(Poll poll){
       return pollRepository.save(poll);
    }

}
