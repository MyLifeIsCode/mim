package com.mim.repository;

import com.mim.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
// extends BaseRepository<Message, Long>,QueryDslPredicateExecutor<Message>
@Component
public interface MessageRepository extends JpaRepository<Message, Long> {


//    Message findById(Long id);
}
