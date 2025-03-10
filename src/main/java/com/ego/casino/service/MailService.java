package com.ego.casino.service;

public interface MailService {

    void sendMail(String to, String subject, String content);
}
