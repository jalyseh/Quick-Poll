package com.example.QuickPoll.repository;

import com.example.QuickPoll.model.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option,Long> {


}
