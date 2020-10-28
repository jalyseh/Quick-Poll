package com.example.QuickPoll.repository;

import com.example.QuickPoll.model.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {


}
