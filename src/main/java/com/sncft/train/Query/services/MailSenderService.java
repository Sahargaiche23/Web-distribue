package com.sncft.train.Query.services;

import com.sncft.train.commonApi.commands.MailSenderDto;

public interface MailSenderService {
    void sendNewMail(MailSenderDto mailSenderDto);

}