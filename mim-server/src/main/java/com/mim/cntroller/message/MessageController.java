package com.mim.cntroller.message;

import com.mim.domain.message.Message;
import com.mim.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private IMessageService messageService;

//    @RequestMapping(value = "/findById")
//    @ResponseBody
//    public Message findById(Long id){
//        return messageService.findById(id);
//    }
}
