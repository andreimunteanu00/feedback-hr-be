package com.feedback.mnt.service;

public interface MailService {

    void sendEmail(String to, String subject, String text);

}
