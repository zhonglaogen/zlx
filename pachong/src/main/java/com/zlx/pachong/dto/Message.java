package com.zlx.pachong.dto;

import com.zlx.pachong.entity.Subject;
import com.zlx.pachong.entity.Title;

public class Message {
    private Subject subject;
    private Title title;

    public Message() {
    }

    public Message(Subject subject, Title title) {
        this.subject = subject;
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
