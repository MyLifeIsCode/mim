package com.mim.service.impl;

import com.mim.domain.message.Message;
import com.mim.domain.message.vo.MessageVo;
import com.mim.repository.MessageRepository;
import com.mim.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }


//    @Override
//    public Message findById(Long id){
//        return messageRepository.findById(id);
//    }






}
