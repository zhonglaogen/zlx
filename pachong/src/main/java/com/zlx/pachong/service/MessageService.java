package com.zlx.pachong.service;

import com.zlx.pachong.dto.Message;
import com.zlx.pachong.entity.Subject;
import com.zlx.pachong.entity.Title;
import com.zlx.pachong.gain.PaChongRong;
import com.zlx.pachong.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public Message saveMessage() throws IOException, IllegalAccessException {

        Message message = PaChongRong.getMessage();
        Subject subject = message.getSubject();
        messageMapper.insertSubject(subject);
        //将自增的主键放入了message
        Title title = message.getTitle();
        title.setSubjectWordId(subject.getSubjectWordId());
        messageMapper.insertTitle(title);
        return message;

    }
}
