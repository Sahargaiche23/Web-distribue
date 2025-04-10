package com.sncft.train.Query.services;

import com.sncft.train.commonApi.commands.MailSenderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendNewMail(MailSenderDto mailSenderDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailSenderDto.getTo());
        message.setSubject(mailSenderDto.getSubject());
        message.setText(mailSenderDto.getBody());
        mailSender.send(message);
    }

}